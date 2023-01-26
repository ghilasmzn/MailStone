package com.client.client.controller.question;

import java.io.File;

import com.client.client.utils.FileSearch;
import com.client.client.utils.XML.XMLWriter;
import com.client.client.utils.XML.message.question.Qcodex001;
import com.client.client.utils.XML.message.question.Qcodex002;
import com.client.client.utils.XML.message.question.Qcodex010;
import com.client.client.utils.XML.message.question.Qcodex100;

public class CreateQuestion implements IQuestion {

    /**
     * Write the question in xml inside a file
     * 
     * @param folder
     * @param ref
     * @param date
     */
    public static void writeQuestionCodex001(File folder, String ref, String date) {

        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "question" + numberFiles + ".xml";

        // Creation of the xml File
        Qcodex001.setData(date, ref, FOLDER_DTD_QUESTION_PRODUCT + SEPARATOR + "001.dtd");
        new XMLWriter().writeXML(FOLDER_QUESTION + SEPARATOR + filename, CODE_QUESTION_PRODUCT_1);
    }

    /**
     * Write the question in xml inside a file
     * 
     * @param folder
     * @param ref
     * @param date
     */
    public static void writeQuestionCodex002(File folder, String ref, String brand) {

        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "question" + numberFiles + ".xml";

        // Creation of the xml File
        Qcodex002.setData(brand, ref, FOLDER_DTD_QUESTION_PRODUCT + SEPARATOR + "002.dtd");
        new XMLWriter().writeXML(FOLDER_QUESTION + SEPARATOR + filename, CODE_QUESTION_PRODUCT_2);
    }

    /**
     * Write the question in xml inside a file
     * 
     * @param folder
     * @param ref
     * @param date
     */
    public static void writeQuestionCodex010(File folder, String description, String email) {

        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "question" + numberFiles + ".xml";

        // Creation of the xml File
        Qcodex010.setData(description, email, FOLDER_DTD_QUESTION_ISSUE + SEPARATOR + "010.dtd");
        new XMLWriter().writeXML(FOLDER_QUESTION + SEPARATOR + filename, CODE_QUESTION_ISSUE_1);
    }

    /**
     * Write the question in xml inside a file
     * 
     * @param folder
     * @param ref
     * @param date
     */
    public static void writeQuestionCodex100(File folder, String ref, String brand, String mail) {

        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "question" + numberFiles + ".xml";

        // Creation of the xml File
        Qcodex100.setData(brand, ref, mail, FOLDER_DTD_QUESTION_CLIENT + SEPARATOR + "100.dtd");
        new XMLWriter().writeXML(FOLDER_QUESTION + SEPARATOR + filename, CODE_QUESTION_CLIENT_1);
    }
}
