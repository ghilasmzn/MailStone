package com.analyzer.analyzer.utils.XML.message.save;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.analyzer.analyzer.model.Issue;

public class Scodex000 {
    private static String dtdPath;
    private static Issue issue;

    public static void setData(Issue i, String dtd) {
        dtdPath = dtd;
        issue = i;
    }

    public static void createXMLContent(OutputStream out, String code) {

        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();

            XMLStreamWriter writer = output.createXMLStreamWriter(out, "UTF-8");

            // Header of the xml file
            writer.writeStartDocument("UTF-8", "1.0");

            writer.writeDTD("<!DOCTYPE code SYSTEM \"" + dtdPath + "\">");

            // <code>
            writer.writeStartElement("issue");
            writer.writeAttribute("code", code);

            // fill the product list
            fillProductList(writer);

            writer.writeEndDocument();
            // </code>

            writer.flush();

            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void fillProductList(XMLStreamWriter writer) throws XMLStreamException {

        // <product>
        writer.writeStartElement("product");

        // <ref>
        writer.writeStartElement("ref");
        writer.writeCharacters(issue.getProduct().getRef());
        writer.writeEndElement();

        // <date>
        writer.writeStartElement("date");
        writer.writeCharacters(issue.getProduct().getDate().toString());
        writer.writeEndElement();

        // <name>
        writer.writeStartElement("name");
        writer.writeCharacters(issue.getProduct().getName().toString());
        writer.writeEndElement();

        // <brand>
        writer.writeStartElement("brand");
        writer.writeCharacters(issue.getProduct().getBrand().toString());
        writer.writeEndElement();

        // <category>
        writer.writeStartElement("category");

        // <name>
        writer.writeStartElement("name");
        writer.writeEndElement();

        writer.writeEndElement();
        // </category>

        // <client>
        writer.writeStartElement("client");

        // <firstname>
        writer.writeStartElement("firstname");
        writer.writeCharacters(issue.getProduct().getClient().getFirstname().toString());
        writer.writeEndElement();

        // <lastname>
        writer.writeStartElement("lastname");
        writer.writeCharacters(issue.getProduct().getClient().getLastname().toString());
        writer.writeEndElement();

        // <email>
        writer.writeStartElement("email");
        writer.writeCharacters(issue.getProduct().getClient().getEmail().toString());
        writer.writeEndElement();

        // <address>
        writer.writeStartElement("address");
        writer.writeCharacters(issue.getProduct().getClient().getAddress().toString());
        writer.writeEndElement();

        // <phoneNumber>
        writer.writeStartElement("phoneNumber");
        writer.writeCharacters(issue.getProduct().getClient().getPhoneNumber().toString());
        writer.writeEndElement();

        writer.writeEndElement();
        // </client>

        writer.writeEndElement();
        // </product>

        // <description>
        writer.writeStartElement("description");
        writer.writeCharacters(issue.getDescription().toString());
        writer.writeEndElement();

        // <resolved>
        writer.writeStartElement("resolved");
        writer.writeCharacters("false");
        writer.writeEndElement();

        // <solution>
        writer.writeStartElement("solution");

        // <description>
        writer.writeStartElement("description");
        writer.writeCharacters("");
        writer.writeEndElement();

        // <cost>
        writer.writeStartElement("cost");
        writer.writeEndElement();

        // <duration>
        writer.writeStartElement("duration");
        writer.writeEndElement();

        writer.writeEndElement();
        // </solution>
    }
}
