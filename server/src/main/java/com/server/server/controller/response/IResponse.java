package com.server.server.controller.response;

import com.server.server.controller.IGloabal;

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
