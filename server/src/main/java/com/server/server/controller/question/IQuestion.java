package com.server.server.controller.question;

import com.server.server.controller.IGloabal;

public interface IQuestion extends IGloabal {

        String FOLDER_QUESTION = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "process"
                        + SEPARATOR + "question";

        String FOLDER_ARCHIVED_QUESTION = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "archived"
                        + SEPARATOR + "question";

        // x001 Message are for PRODUCT REF / DATE
        String CODE_QUESTION_PRODUCT_1 = "q001";
        String CODE_QUESTION_PRODUCT_2 = "q002";

        // x010 Message for Client
        String CODE_QUESTION_ISSUE_1 = "q010";

        // x100 Message for Client
        String CODE_QUESTION_CLIENT_1 = "q100";
}
