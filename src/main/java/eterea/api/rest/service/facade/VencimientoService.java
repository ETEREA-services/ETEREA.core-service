/**
 * 
 */
package eterea.api.rest.service.facade;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Voucher;
import eterea.api.rest.model.view.UsuarioVencimiento;
import eterea.api.rest.service.VoucherService;
import eterea.api.rest.service.view.UsuarioVencimientoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class VencimientoService {

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private UsuarioVencimientoService usuarioVencimientoService;

	@Autowired
	private JavaMailSender sender;

	public String notificaciondia() {

		for (UsuarioVencimiento usuarioVencimiento : usuarioVencimientoService.findAllToday()) {
			log.debug("UsuarioVencimiento -> {}", usuarioVencimiento);
			String data = "";
			for (Voucher voucher : voucherService.findAllByUserToday(usuarioVencimiento.getLogin())) {
				log.debug("Voucher -> {}", voucher);
				data = data + "Vencimiento de Reserva#: " + voucher.getReservaId() + (char) 10;
				data = data + "Nombre Pax: " + voucher.getNombrePax() + (char) 10;
				data = data + "Fecha IN: " + DateTimeFormatter.ofPattern("dd/MM/yyyy")
						.format(voucher.getFechaServicio().withOffsetSameInstant(ZoneOffset.UTC)) + (char) 10;
				data = data + "Productos: " + voucher.getProductos() + (char) 10;
				data = data + "Observaciones: " + voucher.getObservaciones() + (char) 10;
				data = data + "Numero Interno#: " + voucher.getVoucherId() + (char) 10;
				data = data + (char) 10;
				data = data + (char) 10;
			}

			data = data + (char) 10;
			data = data + (char) 10;
			data = data + (char) 10;
			data = data + (char) 10;
			data = data + (char) 10;
			data = data + (char) 10;
			data = data + (char) 10
					+ "Por favor no responda este mail, fue generado automáticamente. Su respuesta no será leída."
					+ (char) 10;

			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			try {
				helper.setTo("agencias@termascacheuta.com");
				helper.setCc("ines.arroyo@termascacheuta.com");
				helper.setBcc("daniel.quinterospinto@gmail.com");
				helper.setText(data);
				helper.setSubject("Aviso de Vencimiento de Programas por el Dia - usuario : "
						+ usuarioVencimiento.getLogin().toUpperCase());
			} catch (MessagingException e) {
				e.printStackTrace();
				return "Error enviando correo ..";
			}
			sender.send(message);
		}

		return "Mail Ok!";
	}

}
