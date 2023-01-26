package com.client.client.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageManager {

    @RequestMapping(value = { "/", "/index" })
    private String redirectUser() {
        return "/home";
    }

    @RequestMapping(value = "/home")
    private String homePage() {
        return "home";
    }

    @RequestMapping(value = "/client")
    private String clientPage() {
        return "client";
    }

    @RequestMapping(value = "/issue")
    private String issuePage() {
        return "issue";
    }
}
