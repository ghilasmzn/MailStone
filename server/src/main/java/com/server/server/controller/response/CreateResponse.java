package com.server.server.controller.response;

import java.io.File;
import java.util.ArrayList;

import com.server.server.model.Issue;
import com.server.server.utils.FileSearch;
import com.server.server.utils.XML.XMLWriter;
import com.server.server.utils.XML.message.response.Rcodex001;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateResponse implements IResponse {

    public void writeResponseCodex001(File folder, ArrayList<Issue> res) {
        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "response" + numberFiles + ".xml";

        // Creation of the xml File
        Rcodex001.setData(res, FOLDER_DTD_RESPONSE + SEPARATOR + "response.dtd");
        new XMLWriter().writeXML(FOLDER_RESPONSE + SEPARATOR + filename, CODE_RESPONSE_PRODUCT);
    }
}
