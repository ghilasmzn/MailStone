package com.client.client.controller.page.response;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import com.client.client.controller.response.ReadResponse;

@Controller
public class GetIssue {

    @GetMapping(value = "/issues/rsp/description-email")
    private String getProductsBrandRef(Model model)
            throws FileNotFoundException, JAXBException, ParserConfigurationException, SAXException {

        new ReadResponse().addViewAttribute(model);
        model.addAttribute("title", "Description et client");
        model.addAttribute("qst", "description-email");

        return "issue/response";
    }
}
