package com.client.client.controller.page.question;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.client.controller.question.CreateQuestion;
import com.client.client.controller.question.IQuestion;

@Controller
public class PostClient implements IQuestion {

    @PostMapping(value = "/clients/qst/mail-brand-ref")
    private String getClientRefBrand(String ref, String brand, String mail, Model model) {

        if (new File(FOLDER_QUESTION).exists()) {
            CreateQuestion.writeQuestionCodex100(new File(FOLDER_QUESTION), ref, brand, mail);
        }

        model.addAttribute("wait", true);
        model.addAttribute("qst", "mail-brand-ref");
        model.addAttribute("title", "Email client, marque et référence produit");

        return "client/response";
    }

    @GetMapping(value = "/client/qst/mail-brand-ref")
    private String clientRefBrand() {
        return "/client/question/mail-brand-ref";
    }
}
