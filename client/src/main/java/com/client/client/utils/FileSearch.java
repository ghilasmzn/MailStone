package com.client.client.utils;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {
    private ArrayList<String> filesInDepth = new ArrayList<String>();

    public FileSearch(File path, int depth) {
        File[] file = path.listFiles();

        if (!path.isDirectory())
            System.out.println("Error ! Path is not directory !");
        else
            this.filesInDepth = fileScanner(file, depth);
    }

    private ArrayList<String> fileScanner(File files[], int depth) {
        if (depth > 0) {
            for (File file : files) {
                filesInDepth.add(file.getName());

                if (file.isDirectory())
                    fileScanner(file.listFiles(), depth - 1);
            }
        }
        return filesInDepth;
    }

    public ArrayList<String> getFileInDepth() {
        return this.filesInDepth;
    }

    public void printFilesInDepth() {
        for (String files : this.filesInDepth)
            System.out.println(files);
    }
}
