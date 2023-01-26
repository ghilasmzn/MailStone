package com.client.client.utils.XML.message.question;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Qcodex010 {
    private static String dtdPath, description, email;

    public static void setData(String d, String e, String dtd) {
        description = d;
        email = e;
        dtdPath = dtd;
    }

    public static void createXMLContent(OutputStream out, String code) {

        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();

            XMLStreamWriter writer = output.createXMLStreamWriter(out, "UTF-8");

            // Header of the xml file
            writer.writeStartDocument("UTF-8", "1.0");

            writer.writeDTD("<!DOCTYPE code SYSTEM \"" + dtdPath + "\">");

            // <code>
            writer.writeStartElement("question");
            writer.writeAttribute("code", code);

            // <product>
            writer.writeStartElement("issue");

            // <ref>
            writer.writeStartElement("descritpion");
            writer.writeCharacters(description.toString());
            writer.writeEndElement();

            // <product>
            writer.writeStartElement("product");

            // <product>
            writer.writeStartElement("client");

            // <email>
            writer.writeStartElement("email");
            writer.writeCharacters(email.toString());
            writer.writeEndElement();

            writer.writeEndElement();
            // </client>

            writer.writeEndElement();
            // </product>

            writer.writeEndElement();
            // </issue>

            writer.writeEndDocument();
            // </code>

            writer.flush();

            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
