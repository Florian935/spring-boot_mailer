package com.florian935.mailer.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SimpleEmail extends Email {

    public SimpleEmail(String toEmail, String body, String subject) {

        super(toEmail, body, subject);
    }
}
