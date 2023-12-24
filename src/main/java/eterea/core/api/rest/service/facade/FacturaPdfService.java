/**
 *
 */
package eterea.core.api.rest.service.facade;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import eterea.core.api.rest.kotlin.model.Cliente;
import eterea.core.api.rest.kotlin.model.ClienteMovimiento;
import eterea.core.api.rest.kotlin.model.Empresa;
import eterea.core.api.rest.kotlin.model.mapper.CodigoQR;
import eterea.core.api.rest.model.*;
import eterea.core.api.rest.service.*;
import eterea.core.api.rest.tool.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;

/**
 * @author daniel
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

    private static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(BLACK, WHITE); // BLACK , WHITE

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        return pngOutputStream.toByteArray();
    }

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
            CodigoQR codigoQR = new CodigoQR();
            codigoQR.setVer(1);
            codigoQR.setFecha(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ToolService.stringDDMMYYYY2OffsetDateTime(electronico.getFecha())));
            codigoQR.setCuit(empresa.getCuit().replaceAll("\\-", ""));
            codigoQR.setPtoVta(electronico.getPuntoVenta());
            codigoQR.setTipoCmp(electronico.getComprobanteId());
            codigoQR.setNroCmp(electronico.getNumeroComprobante());
            codigoQR.setImporte(electronico.getTotal());
            codigoQR.setMoneda("PES");
            codigoQR.setCtz(1);
            codigoQR.setTipoDocRec(electronico.getTipoDocumento());
            codigoQR.setNroDocRec(electronico.getNumeroDocumento());
            codigoQR.setTipoCodAut("E");
            codigoQR.setCodAut(electronico.getCae());
            ObjectMapper objectMapper = new ObjectMapper();
            String datos = new String(Base64.getEncoder().encode(objectMapper.writeValueAsString(codigoQR).getBytes()));
            imageQr = Image.getInstance(getQRCodeImage(url + datos, 25, 25));

        } catch (BadElementException e) {
            log.debug("Sin Imagen");
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
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

        String[] titulo_copias = {"ORIGINAL", "DUPLICADO", "TRIPLICADO"};

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
            for (int pagina = 0; pagina < pdfReader.getNumberOfPages(); ) {
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

        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.setMargins(20, 20, 20, 20);
            document.open();

            table = new PdfPTable(1);
            table.setWidthPercentage(100);
            Paragraph paragraph = new Paragraph(titulo, new Font(Font.COURIER, 16, Font.BOLD, Color.BLACK));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            cell = new PdfPCell();
            cell.addElement(paragraph);
            table.addCell(cell);
            document.add(table);

            table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{48, 4, 48});
            cell = new PdfPCell();
            paragraph = new Paragraph(empresa.getNombreFantasia(), new Font(Font.HELVETICA, 14, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(10);
            cell.addElement(paragraph);
            cell.addElement(new Paragraph(" ", new Font(Font.HELVETICA, 6, Font.NORMAL)));
            paragraph = new Paragraph(new Phrase("Razón Social: ", new Font(Font.HELVETICA, 9, Font.NORMAL)));
            paragraph.add(new Phrase(empresa.getRazonSocial(), new Font(Font.HELVETICA, 10, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(10);
            cell.addElement(paragraph);
            paragraph = new Paragraph(
                    new Phrase("Domicilio Comercial: ", new Font(Font.HELVETICA, 9, Font.NORMAL)));
            paragraph.add(new Phrase(empresa.getDomicilio() + " " + empresa.getTelefono(),
                    new Font(Font.HELVETICA, 10, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(10);
            cell.addElement(paragraph);
            paragraph = new Paragraph(
                    new Phrase("Condición frente al IVA: ", new Font(Font.HELVETICA, 9, Font.NORMAL)));
            paragraph.add(new Phrase(empresa.getCondicionIva(), new Font(Font.HELVETICA, 10, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(10);
            cell.addElement(paragraph);
            table.addCell(cell);
            cell = new PdfPCell();
            paragraph = new Paragraph(comprobante.getLetraComprobante(), new Font(Font.HELVETICA, 24, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("Cod: ", new Font(Font.HELVETICA, 6, Font.NORMAL)));
            paragraph.add(new Phrase(comprobante.getComprobanteAfipId().toString(),
                    new Font(Font.HELVETICA, 6, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(paragraph);
            table.addCell(cell);
            cell = new PdfPCell();
            paragraph = new Paragraph(comprobanteAfip.getLabel(), new Font(Font.HELVETICA, 14, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("Punto de Venta: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(new DecimalFormat("0000").format(electronico.getPuntoVenta()),
                    new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.add(new Phrase("          Comprobante Nro: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(new DecimalFormat("00000000").format(electronico.getNumeroComprobante()),
                    new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("Fecha de Emisión: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(ToolService.stringDDMMYYYY2OffsetDateTime(electronico.getFecha())
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("CUIT: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(empresa.getCuit(), new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("Ingresos Brutos: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(empresa.getIngresosBrutos(), new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(
                    new Phrase("Inicio Actividades: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(empresa.getInicioActividades(), new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            table.addCell(cell);
            document.add(table);
            table = new PdfPTable(1);
            table.setWidthPercentage(100);
            cell = new PdfPCell();
            paragraph = new Paragraph(new Phrase("Cliente: ", new Font(Font.HELVETICA, 10, Font.NORMAL)));
            paragraph.add(new Phrase(cliente.getRazonSocial(), new Font(Font.HELVETICA, 10, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("Domicilio: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(cliente.getDomicilio(), new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(new Phrase("CUIT: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(cliente.getCuit(), new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph
                    .add(new Phrase("                          IVA: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            String[] condiciones = {"Sin Posición", "Responsable Inscripto", "Consumidor Final", "Monotributista",
                    "Responsable No Inscripto", "Exento", "Exportación"};
            paragraph.add(
                    new Phrase(condiciones[cliente.getPosicionIva()], new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            paragraph = new Paragraph(
                    new Phrase("Condición de venta: ", new Font(Font.HELVETICA, 8, Font.NORMAL)));
            paragraph.add(new Phrase(comprobante.getContado() == 0 ? "Cuenta Corriente" : "Contado",
                    new Font(Font.HELVETICA, 8, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(20);
            cell.addElement(paragraph);
            cell.addElement(new Paragraph(" ", new Font(Font.HELVETICA, 6, Font.BOLD)));
            table.addCell(cell);
            document.add(table);

            table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{10, 45, 15, 15, 15});
            cell = new PdfPCell();
            paragraph = new Paragraph("Código", new Font(Font.HELVETICA, 8, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(paragraph);
            table.addCell(cell);
            cell = new PdfPCell();
            paragraph = new Paragraph("Artículo", new Font(Font.HELVETICA, 8, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(paragraph);
            table.addCell(cell);
            cell = new PdfPCell();
            paragraph = new Paragraph("Cantidad", new Font(Font.HELVETICA, 8, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(paragraph);
            table.addCell(cell);
            cell = new PdfPCell();
            paragraph = new Paragraph("Precio Unitario", new Font(Font.HELVETICA, 8, Font.BOLD));
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(paragraph);
            table.addCell(cell);
            cell = new PdfPCell();
            paragraph = new Paragraph("Subtotal", new Font(Font.HELVETICA, 8, Font.BOLD));
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
                table.setWidths(new int[]{10, 45, 15, 15, 15});
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                paragraph = new Paragraph(articuloMovimiento.getArticuloId(),
                        new Font(Font.HELVETICA, 8, Font.NORMAL));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                cell.addElement(paragraph);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                Articulo articulo = articuloService.findByArticuloId(articuloMovimiento.getArticuloId());
                paragraph = new Paragraph(articulo.getDescripcion(), new Font(Font.HELVETICA, 8, Font.NORMAL));
                paragraph.setAlignment(Element.ALIGN_LEFT);
                cell.addElement(paragraph);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                paragraph = new Paragraph(String.valueOf(Math.abs(articuloMovimiento.getCantidad().intValue())),
                        new Font(Font.HELVETICA, 8, Font.NORMAL));
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                cell.addElement(paragraph);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                paragraph = new Paragraph(
                        new DecimalFormat("#.00").format(discriminar ? articuloMovimiento.getPrecioUnitarioSinIva()
                                : articuloMovimiento.getPrecioUnitarioConIva()),
                        new Font(Font.HELVETICA, 8, Font.NORMAL));
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
                        new Font(Font.HELVETICA, 8, Font.NORMAL));
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
                cell.addElement(new Paragraph("  ", new Font(Font.COURIER, 8, Font.NORMAL)));
                table.addCell(cell);
                document.add(table);
            }

            table = new PdfPTable(1);
            table.setWidthPercentage(100);
            paragraph = new Paragraph(new Phrase("Observaciones: ", new Font(Font.COURIER, 10, Font.BOLD)));
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
            paragraph.add(new Phrase(observaciones, new Font(Font.HELVETICA, 10, Font.BOLD)));
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
                        new Font(Font.COURIER, 9, Font.NORMAL)));
                paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getNeto().abs()),
                        new Font(Font.HELVETICA, 9, Font.BOLD)));
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                cell.addElement(paragraph);

                paragraph = new Paragraph(new Phrase("Importe Exento: " + moneda.getSimbolo() + " ",
                        new Font(Font.COURIER, 9, Font.NORMAL)));
                paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getMontoExento().abs()),
                        new Font(Font.HELVETICA, 9, Font.BOLD)));
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                cell.addElement(paragraph);

                paragraph = new Paragraph(new Phrase("Importe IVA 21%: " + moneda.getSimbolo() + " ",
                        new Font(Font.COURIER, 9, Font.NORMAL)));
                paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getMontoIva().abs()),
                        new Font(Font.HELVETICA, 9, Font.BOLD)));
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                cell.addElement(paragraph);

                paragraph = new Paragraph(new Phrase("Importe IVA 10.5%: " + moneda.getSimbolo() + " ",
                        new Font(Font.COURIER, 9, Font.NORMAL)));
                paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getMontoIvaRni().abs()),
                        new Font(Font.HELVETICA, 9, Font.BOLD)));
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                cell.addElement(paragraph);

            }
            paragraph = new Paragraph(new Phrase("Importe Total: $ ", new Font(Font.COURIER, 10, Font.BOLD)));
            paragraph.add(new Phrase(new DecimalFormat("#.00").format(clienteMovimiento.getImporte().abs()),
                    new Font(Font.HELVETICA, 10, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(paragraph);
            table.addCell(cell);
            document.add(table);

            table = new PdfPTable(1);
            table.setWidthPercentage(100);
            paragraph = new Paragraph(new Phrase("CAE Nro: ", new Font(Font.COURIER, 10, Font.NORMAL)));
            paragraph.add(new Phrase(electronico.getCae(), new Font(Font.HELVETICA, 10, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.addElement(paragraph);
            table.addCell(cell);
            document.add(table);

            table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{15, 50, 35});
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
            paragraph = new Paragraph(new Phrase("Vencimiento CAE: ", new Font(Font.COURIER, 10, Font.NORMAL)));
            paragraph.add(new Phrase(electronico.getCaeVencimiento(), new Font(Font.HELVETICA, 10, Font.BOLD)));
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
