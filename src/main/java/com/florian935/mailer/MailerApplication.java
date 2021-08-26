package com.florian935.mailer;

import com.florian935.mailer.model.AttachmentEmail;
import com.florian935.mailer.model.SimpleEmail;
import com.florian935.mailer.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MailerApplication {

    EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(MailerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendSimpleMail() {

        final SimpleEmail simpleEmail = new SimpleEmail(
                "email-to-send",
                "This is the email body",
                "The subject");

        emailSenderService.sendSimpleEmail(simpleEmail);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmailAttachment() {

        final AttachmentEmail attachmentEmail = new AttachmentEmail(
                "email-to-send",
                "This is the email body",
                "The subject",
                "./image/spring-boot.png"
                );

        emailSenderService.sendEmailWithAttachment(attachmentEmail);
    }
}
