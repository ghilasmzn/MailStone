package com.server.server.controller;

import java.io.File;

public interface IGloabal {
    String SEPARATOR = File.separator;

    String PROJECT_PATH = new File("").getAbsolutePath();

    String FOLDER_DTD = PROJECT_PATH
            + SEPARATOR + "communication"
            + SEPARATOR + "DTD";

    String FOLDER_DTD_RESPONSE = FOLDER_DTD
            + SEPARATOR + "response";

    // x001 Message are for PRODUCT REF / DATE
    String CODE_RESPONSE_PRODUCT = "r001";
}
