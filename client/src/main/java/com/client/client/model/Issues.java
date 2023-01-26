package com.client.client.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Issues {
    private String code;

    @XmlElement(name = "issue")
    private ArrayList<Issue> listIssue = new ArrayList<Issue>();

    public Issues(String c, ArrayList<Issue> arr) {
        super();

        code = c;
        listIssue = arr;
    }

    public String getCode() {
        return code;
    }
}
