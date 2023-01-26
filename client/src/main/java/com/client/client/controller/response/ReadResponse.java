package com.client.client.controller.response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.client.client.model.Issue;
import com.client.client.model.Issues;
import com.client.client.utils.FileSearch;
import com.client.client.utils.OSUtil;
import com.client.client.utils.XML.XMLReader;

public class ReadResponse implements IResponse {
    private XMLReader xmlReader = new XMLReader();
    private ArrayList<Issue> list = new ArrayList<>();

    public void addViewAttribute(Model model)
            throws FileNotFoundException, JAXBException, ParserConfigurationException, SAXException {
        // Get the number of archived file
        FileSearch fs = new FileSearch(new File(FOLDER_ARCHIVED_RESPONSE), 1);
        int number = fs.getFileInDepth().size() - 1;
        String name = "response" + number + ".xml";

        convertResponseCodex001(FOLDER_ARCHIVED_RESPONSE + SEPARATOR + name);
        model.addAttribute("nbResultats", list.size());
        model.addAttribute("products", list);
        model.addAttribute("wait", false);
    }

    private void convertResponseCodex001(String file)
            throws JAXBException, ParserConfigurationException, SAXException, FileNotFoundException {

        // ! ******* JAXB / SAX ********
        JAXBContext jc = JAXBContext.newInstance(Issues.class);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        spf.setFeature("http://xml.org/sax/features/validation", false);

        org.xml.sax.XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(file));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        // ! ***************

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Issues p = (Issues) unmarshaller.unmarshal(source);

        list = p.getListIssue();
    }

    /**
     * Process the response, search the code, and content
     * 
     * @param xmlFile
     */
    public void processResponse(String xmlFile) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Retrieve data depending on the code of the message
            String code = xmlReader.getMessageCode(doc);

            // process response with code x001
            if (code.equals(CODE_RESPONSE_PRODUCT))
                convertResponseCodex001(xmlFile.toString());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void readResponse(String filename) {
        File file = null;
        String filePath = FOLDER_RESPONSE + SEPARATOR + filename;
        // Choice of the Path OS
        if (OSUtil.isWindows()) {
            filePath.replace("\\", "\\\\");
            file = new File(filePath);
        } else
            file = new File(FOLDER_RESPONSE + SEPARATOR + filename);

        // Convert the Response into Data Object
        processResponse(filePath);

        // Get the number of archived file
        FileSearch fs = new FileSearch(new File(FOLDER_ARCHIVED_RESPONSE), 1);
        String name = "response" + fs.getFileInDepth().size() + ".xml";

        // Move the file inside the Archive folder
        file.renameTo(new File(FOLDER_ARCHIVED_RESPONSE + SEPARATOR + name));
    }
}