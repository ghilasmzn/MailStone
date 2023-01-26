package com.analyzer.analyzer.controller;

import java.io.File;

public interface IGlobal {
        String SEPARATOR = File.separator;

        String PROJECT_PATH = new File("").getAbsolutePath();

        String FOLDER_DTD = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "DTD";

        String FOLDER_DTD_SAVE = FOLDER_DTD + SEPARATOR + "save";

        String FOLDER_ARCHIVED_SAVE = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "archived"
                        + SEPARATOR + "save";

        // x000 Message save
        String CODE_SAVE_OBJECT = "s000";
}
