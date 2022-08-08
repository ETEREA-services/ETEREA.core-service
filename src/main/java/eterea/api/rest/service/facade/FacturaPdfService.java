/**
 * 
 */
package eterea.api.rest.service.facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import eterea.api.rest.model.Articulo;
import eterea.api.rest.model.ArticuloMovimiento;
import eterea.api.rest.model.Cliente;
import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.model.Comprobante;
import eterea.api.rest.model.ComprobanteAfip;
import eterea.api.rest.model.Electronico;
import eterea.api.rest.model.Empresa;
import eterea.api.rest.model.Moneda;
import eterea.api.rest.service.ArticuloMovimientoService;
import eterea.api.rest.service.ArticuloService;
import eterea.api.rest.service.ClienteMovimientoService;
import eterea.api.rest.service.ClienteService;
import eterea.api.rest.service.ComprobanteAfipService;
import eterea.api.rest.service.ComprobanteService;
import eterea.api.rest.service.ElectronicoService;
import eterea.api.rest.service.EmpresaService;
import eterea.api.rest.service.MonedaService;
import eterea.api.rest.tool.ToolService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class FacturaPdfService {

	@Autowired
	private Environment env;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ClienteMovimientoService clienteMovimientoService;

	@Autowired
	private ElectronicoService electronicoService;

	@Autowired
	private ComprobanteService comprobanteService;

	@Autowired
	private ComprobanteAfipService comprobanteAfipService;

	@Autowired
	private ArticuloMovimientoService articuloMovimientoService;

	@Autowired
	private ArticuloService articuloService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private MonedaService monedaService;

	public String generatePdf(Long clienteMovimientoId) {

		Image imageQr = null;
		Empresa empresa = empresaService.findTop();
		ClienteMovimiento clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);
		Cliente cliente = clienteService.findByClienteId(clienteMovimiento.getClienteId());
		Electronico electronico = electronicoService.findByUnique(clienteMovimiento.getComprobanteId(),
				clienteMovimiento.getPuntoVenta(), clienteMovimiento.getNumeroComprobante());
		Moneda moneda = monedaService.findByMonedaId(clienteMovimiento.getMonedaId());

		ClienteMovimiento clienteMovimientoAsociado = null;
		ComprobanteAfip comprobanteAfipAsociado = null;
		if (electronico.getClienteMovimientoIdAsociado() != null) {
			clienteMovimientoAsociado = clienteMovimientoService
					.findByClienteMovimientoId(electronico.getClienteMovimientoIdAsociado());
			log.debug("ClienteMovimientoAsociado -> {}", clienteMovimientoAsociado);
			Comprobante comprobante = comprobanteService
					.findByComprobanteId(clienteMovimientoAsociado.getComprobanteId());
			comprobanteAfipAsociado = comprobanteAfipService
					.findByComprobanteAfipId(comprobante.getComprobanteAfipId());
		}

		try {
			String url = "https://www.afip.gob.ar/fe/qr/?p=";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ver", 1);
			jsonObject.put("fecha", DateTimeFormatter.ofPattern("yyyy-MM-dd")
					.format(ToolService.stringDDMMYYYY2OffsetDateTime(electronico.getFecha())));
			jsonObject.put("cuit", Long.parseLong(empresa.getCuit().replaceAll("\\-", "")));
			jsonObject.put("ptoVta", electronico.getPuntoVenta());
			jsonObject.put("tipoCmp", electronico.getComprobanteId());
			jsonObject.put("nroCmp", electronico.getNumeroComprobante());
			jsonObject.put("importe", electronico.getTotal());
			jsonObject.put("moneda", "PES");
			jsonObject.put("ctz", 1);
			jsonObject.put("tipoDocRec", electronico.getTipoDocumento());
			jsonObject.put("nroDocRec", electronico.getNumeroDocumento());
			jsonObject.put("tipoCodAut", "E");
			jsonObject.put("codAut", new BigDecimal(electronico.getCae()));
			String datos = new String(Base64.getEncoder().encode(jsonObject.toString().getBytes()));
			BarcodeQRCode code = new BarcodeQRCode(url + datos, 1, 1, null);
			imageQr = code.getImage();
		} catch (BadElementException e) {
			log.debug("Sin Imagen");
		}

		Comprobante comprobante = comprobanteService.findByComprobanteId(electronico.getComprobanteId());
		Boolean discrimina = false;
		Integer copias = 2;
		List<String> discriminados = Arrays.asList("A", "M");
		if (discriminados.contains(comprobante.getLetraComprobante())) {
			discrimina = true;
			copias = 3;
		}
		ComprobanteAfip comprobanteAfip = comprobanteAfipService
				.findByComprobanteAfipId(comprobante.getComprobanteAfipId());

		String[] titulo_copias = { "ORIGINAL", "DUPLICADO", "TRIPLICADO" };

		String path = env.getProperty("path.facturas");

		String filename = "";
		List<String> filenames = new ArrayList<>();
		for (int copia = 0; copia < copias; copia++) {
			filenames.add(filename = path + clienteMovimientoId + "." + titulo_copias[copia].toLowerCase() + ".pdf");

			makePage(filename, titulo_copias[copia], empresa, comprobante, comprobanteAfip, electronico, cliente,
					clienteMovimiento, moneda, discrimina, imageQr, clienteMovimientoAsociado, comprobanteAfipAsociado);
		}

		try {
			mergePdf(filename = path + clienteMovimientoId + ".pdf", filenames);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filename;
	}

	private void mergePdf(String filename, List<String> filenames) throws DocumentException, IOException {
		OutputStream outputStream = new FileOutputStream(new File(filename));
		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
		document.open();
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
		for (String name : filenames) {
			PdfReader pdfReader = new PdfReader(new FileInputStream(new File(name)));
			for (int pagina = 0; pagina < pdfReader.getNumberOfPages();) {
				document.newPage();
				PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, ++pagina);
				pdfContentByte.addTemplate(page, 0, 0);
			}
		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}

	private void makePage(String filename, String titulo, Empresa empresa, Comprobante comprobante,
			ComprobanteAfip comprobanteAfip, Electronico electronico, Cliente cliente,
			ClienteMovimiento clienteMovimiento, Moneda moneda, Boolean discriminar, Image imageQr,
			ClienteMovimiento clienteMovimientoAsociado, ComprobanteAfip comprobanteAfipAsociado) {
		PdfPTable table = null;
		PdfPCell cell = null;

		Document document = new Document(new Rectangle(PageSize.A4));
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.setMargins(20, 20, 20, 20);
			document.open();

			table = new PdfPTable(1);
			table.setWidthPercentage(100);
			Paragraph paragraph = new Paragraph(titulo, new Font(FontFamily.COURIER, 16, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell = new PdfPCell();
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);

			table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 48, 4, 48 });
			cell = new PdfPCell();
			paragraph = new Paragraph(empresa.getNombreFantasia(), new Font(FontFamily.HELVETICA, 14, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(10);
			cell.addElement(paragraph);
			cell.addElement(new Paragraph(" ", new Font(FontFamily.HELVETICA, 6, Font.NORMAL)));
			paragraph = new Paragraph(new Phrase("Razón Social: ", new Font(FontFamily.HELVETICA, 9, Font.NORMAL)));
			paragraph.add(new Phrase(empresa.getRazonSocial(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(10);
			cell.addElement(paragraph);
			paragraph = new Paragraph(
					new Phrase("Domicilio Comercial: ", new Font(FontFamily.HELVETICA, 9, Font.NORMAL)));
			paragraph.add(new Phrase(empresa.getDomicilio() + " " + empresa.getTelefono(),
					new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(10);
			cell.addElement(paragraph);
			paragraph = new Paragraph(
					new Phrase("Condición frente al IVA: ", new Font(FontFamily.HELVETICA, 9, Font.NORMAL)));
			paragraph.add(new Phrase(empresa.getCondicionIva(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(10);
			cell.addElement(paragraph);
			table.addCell(cell);
			cell = new PdfPCell();
			paragraph = new Paragraph(comprobante.getLetraComprobante(), new Font(FontFamily.HELVETICA, 24, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("Cod: ", new Font(FontFamily.HELVETICA, 6, Font.NORMAL)));
			paragraph.add(new Phrase(comprobante.getComprobanteAfipId().toString(),
					new Font(FontFamily.HELVETICA, 6, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			table.addCell(cell);
			cell = new PdfPCell();
			paragraph = new Paragraph(comprobanteAfip.getLabel(), new Font(FontFamily.HELVETICA, 14, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("Punto de Venta: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(new DecimalFormat("0000").format(electronico.getPuntoVenta()),
					new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.add(new Phrase("          Comprobante Nro: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(new DecimalFormat("00000000").format(electronico.getNumeroComprobante()),
					new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("Fecha de Emisión: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(ToolService.stringDDMMYYYY2OffsetDateTime(electronico.getFecha())
					.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("CUIT: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(empresa.getCuit(), new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("Ingresos Brutos: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(empresa.getIngresosBrutos(), new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(
					new Phrase("Inicio Actividades: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(empresa.getInicioActividades(), new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);
			table = new PdfPTable(1);
			table.setWidthPercentage(100);
			cell = new PdfPCell();
			paragraph = new Paragraph(new Phrase("Cliente: ", new Font(FontFamily.HELVETICA, 10, Font.NORMAL)));
			paragraph.add(new Phrase(cliente.getRazonSocial(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("Domicilio: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(cliente.getDomicilio(), new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(new Phrase("CUIT: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(cliente.getCuit(), new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph
					.add(new Phrase("                          IVA: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			String[] condiciones = { "Sin Posición", "Responsable Inscripto", "Consumidor Final", "Monotributista",
					"Responsable No Inscripto", "Exento", "Exportación" };
			paragraph.add(
					new Phrase(condiciones[cliente.getPosicionIva()], new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			paragraph = new Paragraph(
					new Phrase("Condición de venta: ", new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
			paragraph.add(new Phrase(comprobante.getContado() == 0 ? "Cuenta Corriente" : "Contado",
					new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			paragraph.setIndentationLeft(20);
			cell.addElement(paragraph);
			cell.addElement(new Paragraph(" ", new Font(FontFamily.HELVETICA, 6, Font.BOLD)));
			table.addCell(cell);
			document.add(table);

			table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 10, 45, 15, 15, 15 });
			cell = new PdfPCell();
			paragraph = new Paragraph("Código", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(paragraph);
			table.addCell(cell);
			cell = new PdfPCell();
			paragraph = new Paragraph("Artículo", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(paragraph);
			table.addCell(cell);
			cell = new PdfPCell();
			paragraph = new Paragraph("Cantidad", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(paragraph);
			table.addCell(cell);
			cell = new PdfPCell();
			paragraph = new Paragraph("Precio Unitario", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(paragraph);
			table.addCell(cell);
			cell = new PdfPCell();
			paragraph = new Paragraph("Subtotal", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);

			Integer lineas = 25;

			for (ArticuloMovimiento articuloMovimiento : articuloMovimientoService
					.findAllByClienteMovimientoId(clienteMovimiento.getClienteMovimientoId())) {
				lineas--;
				table = new PdfPTable(5);
				table.setWidthPercentage(100);
				table.setWidths(new int[] { 10, 45, 15, 15, 15 });
				cell = new PdfPCell();
				cell.setBorder(Rectangle.NO_BORDER);
				paragraph = new Paragraph(articuloMovimiento.getArticuloId(),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(paragraph);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.setBorder(Rectangle.NO_BORDER);
				Articulo articulo = articuloService.findByArticuloId(articuloMovimiento.getArticuloId());
				paragraph = new Paragraph(articulo.getDescripcion(), new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				paragraph.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(paragraph);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.setBorder(Rectangle.NO_BORDER);
				paragraph = new Paragraph(String.valueOf(Math.abs(articuloMovimiento.getCantidad().intValue())),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.setBorder(Rectangle.NO_BORDER);
				paragraph = new Paragraph(
						new DecimalFormat("#.00").format(discriminar ? articuloMovimiento.getPrecioUnitarioSinIva()
								: articuloMovimiento.getPrecioUnitarioConIva()),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.setBorder(Rectangle.NO_BORDER);
				paragraph = new Paragraph(
						new DecimalFormat("#.00").format(Math.abs(articuloMovimiento.getCantidad()
								.multiply((discriminar ? articuloMovimiento.getPrecioUnitarioSinIva()
										: articuloMovimiento.getPrecioUnitarioConIva()))
								.doubleValue())),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);
				table.addCell(cell);
				document.add(table);
			}

			for (int i = 0; i < lineas; i++) {
				table = new PdfPTable(1);
				table.setWidthPercentage(100);
				cell = new PdfPCell();
				cell.setBorder(Rectangle.NO_BORDER);
				cell.addElement(new Paragraph("  ", new Font(FontFamily.COURIER, 8, Font.NORMAL)));
				table.addCell(cell);
				document.add(table);
			}

			table = new PdfPTable(1);
			table.setWidthPercentage(100);
			paragraph = new Paragraph(new Phrase("Observaciones: ", new Font(FontFamily.COURIER, 10, Font.BOLD)));
			String observaciones = "";
			if (electronico.getClienteMovimientoIdAsociado() != null) {
				observaciones += "Asoc: ";
				if (comprobanteAfipAsociado != null) {
					observaciones += comprobanteAfipAsociado.getLabel();
				}
				if (clienteMovimientoAsociado != null) {
					observaciones += clienteMovimientoAsociado.getLetraComprobante()
							+ String.format("%04d", clienteMovimientoAsociado.getPuntoVenta()) + "-"
							+ String.format("%08d", clienteMovimientoAsociado.getNumeroComprobante());
				}
			}
			paragraph.add(new Phrase(observaciones, new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			cell = new PdfPCell();
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);

			table = new PdfPTable(1);
			table.setWidthPercentage(100);
			cell = new PdfPCell();
			if (discriminar) {
				paragraph = new Paragraph(new Phrase("Importe Neto: " + moneda.getSimbolo() + " ",
						new Font(FontFamily.COURIER, 9, Font.NORMAL)));
				paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getNeto().abs()),
						new Font(FontFamily.HELVETICA, 9, Font.BOLD)));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);

				paragraph = new Paragraph(new Phrase("Importe Exento: " + moneda.getSimbolo() + " ",
						new Font(FontFamily.COURIER, 9, Font.NORMAL)));
				paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getMontoExento().abs()),
						new Font(FontFamily.HELVETICA, 9, Font.BOLD)));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);

				paragraph = new Paragraph(new Phrase("Importe IVA 21%: " + moneda.getSimbolo() + " ",
						new Font(FontFamily.COURIER, 9, Font.NORMAL)));
				paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getMontoIva().abs()),
						new Font(FontFamily.HELVETICA, 9, Font.BOLD)));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);

				paragraph = new Paragraph(new Phrase("Importe IVA 10.5%: " + moneda.getSimbolo() + " ",
						new Font(FontFamily.COURIER, 9, Font.NORMAL)));
				paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getMontoIvaRni().abs()),
						new Font(FontFamily.HELVETICA, 9, Font.BOLD)));
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(paragraph);

			}
			paragraph = new Paragraph(new Phrase("Importe Total: $ ", new Font(FontFamily.COURIER, 10, Font.BOLD)));
			paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getImporte().abs()),
					new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);

			table = new PdfPTable(1);
			table.setWidthPercentage(100);
			paragraph = new Paragraph(new Phrase("CAE Nro: ", new Font(FontFamily.COURIER, 10, Font.NORMAL)));
			paragraph.add(new Phrase(electronico.getCae(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			cell = new PdfPCell();
			cell.setBorder(Rectangle.NO_BORDER);
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);

			table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 15, 50, 35 });
			// Agrega código QR
			cell = new PdfPCell();
			cell.setBorder(Rectangle.NO_BORDER);
			cell.addElement(imageQr);
			table.addCell(cell);
			//
			cell = new PdfPCell();
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			// Agrega Vencimiento
			paragraph = new Paragraph(new Phrase("Vencimiento CAE: ", new Font(FontFamily.COURIER, 10, Font.NORMAL)));
			paragraph.add(new Phrase(electronico.getCaeVencimiento(), new Font(FontFamily.HELVETICA, 10, Font.BOLD)));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			cell = new PdfPCell();
			cell.setBorder(Rectangle.NO_BORDER);
			cell.addElement(paragraph);
			table.addCell(cell);
			document.add(table);

			document.close();
		} catch (Exception ex) {
			log.debug(ex.getMessage().toString());
		}

	}

}
