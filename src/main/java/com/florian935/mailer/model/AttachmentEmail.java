package com.florian935.mailer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AttachmentEmail extends Email {

    String attachmentPath;

    public AttachmentEmail(String toEmail, String body, String subject, String attachmentPath) {

        super(toEmail, body, subject);
        this.attachmentPath = attachmentPath;
    }
}
