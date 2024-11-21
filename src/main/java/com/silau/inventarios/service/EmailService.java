package com.silau.inventarios.service;

import com.silau.inventarios.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public EmailDTO sendEmails(EmailDTO emailDTO) {
        try {
            // Enviar el correo a ti mismo con la información del cliente
            sendEmailToAdmin(emailDTO);

            // Enviar el correo al cliente con el mensaje de agradecimiento
            sendEmailToCustomer(emailDTO);
            return emailDTO;
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            // Puedes registrar un log o manejar la excepción según sea necesario
            return null;
        }
    }

    private void sendEmailToAdmin(EmailDTO emailDTO) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("silausas.oficial@gmail.com");  // Tu correo
        helper.setSubject("Nuevo mensaje de cliente");
        helper.setText("Tienes un nuevo mensaje de un cliente.\n\n"
                + "Nombre: " + emailDTO.getNombre() + "\n"
                + "Email: " + emailDTO.getEmail() + "\n"
                + "Número de contacto: " + emailDTO.getNumeroContacto() + "\n"
                + "Comentario: " + emailDTO.getComentario(), true);  // Si 'true', se puede usar HTML

        emailSender.send(message);
    }

    private void sendEmailToCustomer(EmailDTO emailDTO) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailDTO.getEmail());  // El correo del cliente
        helper.setSubject("Gracias por comunicarte con nosotros");
        helper.setText("Estimado(a) " + emailDTO.getNombre() + ",\n\n"
                + "Gracias por comunicarte con nosotros. Hemos recibido tu mensaje y nos pondremos en contacto contigo pronto.\n\n"
                + "Atentamente,\nEl equipo de atención al cliente", true);

        emailSender.send(message);
    }

}
