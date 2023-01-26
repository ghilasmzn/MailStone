package com.client.client.utils.XML.message.question;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Qcodex001 {
    private static String dtdPath, date, ref;

    public static void setData(String d, String r, String dtd) {
        date = d;
        ref = r;
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

            // <date>
            writer.writeStartElement("date");
            writer.writeCharacters(date.toString());
            writer.writeEndElement();

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
