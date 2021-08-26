package com.florian935.mailer.service;

import com.florian935.mailer.model.AttachmentEmail;
import com.florian935.mailer.model.SimpleEmail;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import java.io.File;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class EmailSenderService {

    final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String fromEmail;

    public void sendSimpleEmail(SimpleEmail simpleEmail) {

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(simpleEmail.getToEmail());
        message.setText(simpleEmail.getBody());
        message.setSubject(simpleEmail.getSubject());

        mailSender.send(message);
    }

    @SneakyThrows
    public void sendEmailWithAttachment(AttachmentEmail attachmentEmail) {

        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper =
                buildMimeMessageHelper(mimeMessage, attachmentEmail);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private MimeMessageHelper buildMimeMessageHelper(MimeMessage mimeMessage, AttachmentEmail attachmentEmail) {

        final MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setTo(attachmentEmail.getToEmail());
        mimeMessageHelper.setText(attachmentEmail.getBody());
        mimeMessageHelper.setSubject(attachmentEmail.getSubject());

        final FileSystemResource fileSystemResource =
                new FileSystemResource(new File(attachmentEmail.getAttachmentPath()));

        mimeMessageHelper.addAttachment(attachmentEmail.getAttachmentPath(), fileSystemResource);

        return mimeMessageHelper;
    }
}
