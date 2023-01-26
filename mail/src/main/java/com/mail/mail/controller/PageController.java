package com.mail.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mail.mail.model.Mail;
import com.mail.mail.service.MailService;

@Controller
public class PageController {

    @GetMapping(value = "/")
    public String getIndex() {

        return "/index";
    }

    @PostMapping(value = "/send-email")
    private String senEmail(Mail m) {

        System.out.println(m.toString());
        System.out.println("Sending Email...");

        new MailService().send(m);

        System.out.println("Done");

        return "redirect:/done";
    }

    @GetMapping(value = "/done")
    private String getDonePage() {
        return "mail";
    }
}
