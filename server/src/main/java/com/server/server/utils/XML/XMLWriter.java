package com.server.server.utils.XML;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.server.server.controller.IGloabal;
import com.server.server.utils.XML.message.response.Rcodex001;

public class XMLWriter implements IGloabal {

    private static String formatXML(String xml) throws TransformerException {

        // write data to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print by indention
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));

        return output.toString();
    }

    public void writeXML(String path, String code) {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            chooseTypeMessage(out, code);

            // standard way to convert byte array to String
            String xml = new String(out.toByteArray(), StandardCharsets.UTF_8);
            String prettyPrintXML = formatXML(xml);

            // write to file
            Files.writeString(Paths.get(path), prettyPrintXML, StandardCharsets.UTF_8);

        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void chooseTypeMessage(ByteArrayOutputStream out, String code) {
        switch (code) {

            // message response : liste des produits de ref X et de date Y
            case CODE_RESPONSE_PRODUCT:
                // write XML to ByteArrayOutputStream
                Rcodex001.createXMLContent(out, code);
                break;
        }
    }
}
