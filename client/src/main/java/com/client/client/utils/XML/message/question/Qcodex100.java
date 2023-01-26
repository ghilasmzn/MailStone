package com.client.client.utils.XML.message.question;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Qcodex100 {
    private static String dtdPath, brand, ref, mail;

    public static void setData(String b, String r, String m, String dtd) {
        brand = b;
        ref = r;
        mail = m;
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
            writer.writeStartElement("product");

            // <ref>
            writer.writeStartElement("ref");
            writer.writeCharacters(ref.toString());
            writer.writeEndElement();

            // <brand>
            writer.writeStartElement("brand");
            writer.writeCharacters(brand.toString());
            writer.writeEndElement();

            // <client>
            writer.writeStartElement("client");

            // <mail>
            writer.writeStartElement("email");
            writer.writeCharacters(mail.toString());
            writer.writeEndElement();

            writer.writeEndElement();
            // </client>

            writer.writeEndElement();
            // </product>

            writer.writeEndDocument();
            // </code>

            writer.flush();

            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
