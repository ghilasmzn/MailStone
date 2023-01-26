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
public class GetProduct {

    @GetMapping(value = "/products/rsp/ref-date")
    private String getProductsDateRef(Model model)
            throws FileNotFoundException, JAXBException, ParserConfigurationException, SAXException {

        new ReadResponse().addViewAttribute(model);
        model.addAttribute("title", "Date de panne et référence");
        model.addAttribute("qst", "ref-date");

        return "product/response";
    }

    @GetMapping(value = "/products/rsp/ref-brand")
    private String getProductsBrandRef(Model model)
            throws FileNotFoundException, JAXBException, ParserConfigurationException, SAXException {

        new ReadResponse().addViewAttribute(model);
        model.addAttribute("title", "Marque et référence");
        model.addAttribute("qst", "ref-brand");

        return "product/response";
    }

}
