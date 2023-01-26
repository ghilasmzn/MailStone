package com.client.client.controller.page.question;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.client.controller.question.CreateQuestion;
import com.client.client.controller.question.IQuestion;

@Controller
public class PostIssue implements IQuestion {

    @PostMapping(value = "/issues/qst/description-email")
    private String getClientRefBrand(String email, String description, Model model) {

        if (new File(FOLDER_QUESTION).exists()) {
            CreateQuestion.writeQuestionCodex010(new File(FOLDER_QUESTION), description, email);
        }

        model.addAttribute("wait", true);
        model.addAttribute("qst", "description-email");
        model.addAttribute("title", "Description et client");

        return "issue/response";
    }

    @GetMapping(value = "/issue/qst/description-email")
    private String clientRefBrand() {
        return "/issue/question/description-email";
    }
}
