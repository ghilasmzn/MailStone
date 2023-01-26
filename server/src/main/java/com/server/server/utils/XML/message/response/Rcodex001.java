package com.server.server.utils.XML.message.response;

import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.server.server.model.Issue;

public class Rcodex001 {
    private static String dtdPath;
    private static ArrayList<Issue> arr = new ArrayList<Issue>();

    public static void setData(ArrayList<Issue> arrIssue, String dtd) {
        dtdPath = dtd;
        arr = arrIssue;
    }

    public static void createXMLContent(OutputStream out, String code) {

        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();

            XMLStreamWriter writer = output.createXMLStreamWriter(out, "UTF-8");

            // Header of the xml file
            writer.writeStartDocument("UTF-8", "1.0");

            writer.writeDTD("<!DOCTYPE code SYSTEM \"" + dtdPath + "\">");

            // <code>
            writer.writeStartElement("issues");
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
        for (Issue i : arr) {
            // <issue>
            writer.writeStartElement("issue");

            // <product>
            writer.writeStartElement("product");

            // <ref>
            writer.writeStartElement("ref");
            writer.writeCharacters(toString(i.getProduct().getRef()));
            writer.writeEndElement();

            // <date>
            writer.writeStartElement("date");
            writer.writeCharacters(toString(i.getProduct().getDate()));
            writer.writeEndElement();

            // <name>
            writer.writeStartElement("name");
            writer.writeCharacters(toString(i.getProduct().getName()));
            writer.writeEndElement();

            // <brand>
            writer.writeStartElement("brand");
            writer.writeCharacters(toString(i.getProduct().getBrand()));
            writer.writeEndElement();

            // <category>
            writer.writeStartElement("category");

            // <name>
            writer.writeStartElement("name");
            writer.writeCharacters(toString(i.getProduct().getCategory().getName()));
            writer.writeEndElement();

            writer.writeEndElement();
            // </category>

            // <client>
            writer.writeStartElement("client");

            // <firstname>
            writer.writeStartElement("firstname");
            writer.writeCharacters(toString(i.getProduct().getClient().getFirstname()));
            writer.writeEndElement();

            // <lastname>
            writer.writeStartElement("lastname");
            writer.writeCharacters(toString(i.getProduct().getClient().getLastname()));
            writer.writeEndElement();

            // <email>
            writer.writeStartElement("email");
            writer.writeCharacters(toString(i.getProduct().getClient().getEmail()));
            writer.writeEndElement();

            // <address>
            writer.writeStartElement("address");
            writer.writeCharacters(toString(i.getProduct().getClient().getAddress()));
            writer.writeEndElement();

            // <phoneNumber>
            writer.writeStartElement("phoneNumber");
            writer.writeCharacters(toString(i.getProduct().getClient().getPhoneNumber()));
            writer.writeEndElement();

            writer.writeEndElement();
            // </client>

            writer.writeEndElement();
            // </product>

            // <description>
            writer.writeStartElement("description");
            writer.writeCharacters(toString(i.getDescription()));
            writer.writeEndElement();

            // <resolved>
            writer.writeStartElement("resolved");
            writer.writeCharacters(Boolean.toString(i.isResolved()));
            writer.writeEndElement();

            // <solution>
            writer.writeStartElement("solution");

            // <description>
            writer.writeStartElement("description");
            writer.writeCharacters(toString(i.getSolution().getDescription()));
            writer.writeEndElement();

            // <cost>
            writer.writeStartElement("cost");
            writer.writeCharacters(LongtoString(i.getSolution().getCost()));
            writer.writeEndElement();

            // <cost>
            writer.writeStartElement("duration");
            writer.writeCharacters(LongtoString(i.getSolution().getDuration()));
            writer.writeEndElement();

            writer.writeEndElement();
            // </solution>

            writer.writeEndElement();
            // </issue>
        }
    }

    private static String toString(Object o) {
        if (o == null)
            return "";

        return o.toString();
    }

    private static String LongtoString(Long i) {
        if (i == null)
            return "";

        return Long.toString(i);
    }
}
