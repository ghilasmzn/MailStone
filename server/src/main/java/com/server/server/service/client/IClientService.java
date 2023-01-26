package com.server.server.service.client;

import com.server.server.model.Client;

public interface IClientService {
    public Client findClientByEmail(String mail);
}
