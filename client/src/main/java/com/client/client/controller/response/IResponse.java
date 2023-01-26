package com.client.client.controller.response;

import com.client.client.controller.IGloabal;

public interface IResponse extends IGloabal {
        String FOLDER_RESPONSE = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "process"
                        + SEPARATOR + "response";

        String FOLDER_ARCHIVED_RESPONSE = PROJECT_PATH
                        + SEPARATOR + "communication"
                        + SEPARATOR + "archived"
                        + SEPARATOR + "response";
}
