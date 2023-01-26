package com.server.server.controller.Http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.server.server.model.Client;
import com.server.server.model.Issue;
import com.server.server.model.Product;
import com.server.server.service.client.ClientService;
import com.server.server.service.issue.IssueService;

@Controller
public class CatchPostRequest {

    @Autowired
    private IssueService serviceIssue;

    @Autowired
    private ClientService serviceClient;

    @PostMapping(value = "/ok", headers = {
            "Content-type=application/xml" }, consumes = MediaType.APPLICATION_XML_VALUE)
    private String hello(@RequestBody Issue p) {
        System.out.println("****************!!!!!");
        System.out.println(p.toString());

        Client c = serviceClient.findClientByEmail(p.getProduct().getClient().getEmail());

        Product pro;
        if (c == null) {
            serviceIssue.save(p);
        } else {
            pro = p.getProduct();
            pro.setClient(c);
            serviceIssue.save(p);
        }

        return "index";
    }

}
