package com.server.server.controller.question;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.server.server.utils.FileSearch;
import com.server.server.utils.OSUtil;
import com.server.server.utils.XML.XMLReader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReadQuestion implements IQuestion {

    private XMLReader xmlReader = new XMLReader();

    /**
     * Process the question, search the code, and content
     * and convert the question to SQL
     * 
     * @param xmlFile
     */
    public void processQuestion(File xmlFile) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Covert the message to SQL depending the code of the message
            String code = xmlReader.getMessageCode(doc);
            System.out.println("PROCESS QUESTION");

            switch (code) {
                // message question : lister les produits par ref et date issue
                case CODE_QUESTION_PRODUCT_1:
                    // convert question with code x001
                    new ConvertQuestion().codex001(doc);
                    break;

                case CODE_QUESTION_PRODUCT_2:
                    // convert question with code x002
                    new ConvertQuestion().codex002(doc);
                    break;

                case CODE_QUESTION_ISSUE_1:
                    // convert question with code x100
                    new ConvertQuestion().codex010(doc);
                    break;

                case CODE_QUESTION_CLIENT_1:
                    // convert question with code x100
                    new ConvertQuestion().codex100(doc);
                    break;
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read a question from the QESTION folder
     * 
     * @param filename
     */
    public void readQuestion(String filename) {
        File file = null;

        // Choice of the Path OS
        if (OSUtil.isWindows()) {
            String filePath = FOLDER_QUESTION + SEPARATOR + filename;

            filePath.replace("\\", "\\\\");
            file = new File(filePath);
        } else
            file = new File(FOLDER_QUESTION + SEPARATOR + filename);

        // Convert the Question into SQL
        processQuestion(file);

        // Get the number of archived file
        FileSearch fs = new FileSearch(new File(FOLDER_ARCHIVED_QUESTION), 1);
        String name = "question" + fs.getFileInDepth().size() + ".xml";

        // Move the file inside the Archive folder
        file.renameTo(new File(FOLDER_ARCHIVED_QUESTION + SEPARATOR + name));
    }
}
