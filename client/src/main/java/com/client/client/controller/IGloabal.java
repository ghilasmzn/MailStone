package com.client.client.controller;

import java.io.File;

public interface IGloabal {
        String SEPARATOR = File.separator;

        String PROJECT_PATH = new File("").getAbsolutePath();

        String FOLDER_DTD = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "DTD";

        String FOLDER_DTD_QUESTION_CLIENT = FOLDER_DTD
                        + SEPARATOR + "question"
                        + SEPARATOR + "client";

        String FOLDER_DTD_QUESTION_ISSUE = FOLDER_DTD
                        + SEPARATOR + "question"
                        + SEPARATOR + "issue";

        String FOLDER_DTD_QUESTION_PRODUCT = FOLDER_DTD
                        + SEPARATOR + "question"
                        + SEPARATOR + "product";

        String FOLDER_RESPONSE = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "process"
                        + SEPARATOR + "response";

        // x001 Message are for PRODUCT REF / DATE
        String CODE_QUESTION_PRODUCT_1 = "q001";
        String CODE_QUESTION_PRODUCT_2 = "q002";

        // x010 Message for Client
        String CODE_QUESTION_ISSUE_1 = "q010";

        // x100 Message for Client
        String CODE_QUESTION_CLIENT_1 = "q100";

        String CODE_RESPONSE_PRODUCT = "r001";
}
