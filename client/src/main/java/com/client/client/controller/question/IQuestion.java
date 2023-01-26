package com.client.client.controller.question;

import com.client.client.controller.IGloabal;

public interface IQuestion extends IGloabal {

    String FOLDER_QUESTION = PROJECT_PATH
            + SEPARATOR + "communication"
            + SEPARATOR + "process"
            + SEPARATOR + "question";

    String FOLDER_ARCHIVED_QUESTION = PROJECT_PATH
            + SEPARATOR + "communication"
            + SEPARATOR + "archived"
            + SEPARATOR + "question";
}
