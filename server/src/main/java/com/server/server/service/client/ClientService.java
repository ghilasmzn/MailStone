package com.server.server.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.server.model.Client;
import com.server.server.repository.ClientRepository;

@Service
public class ClientService implements IClientService {

    @Autowired
    ClientRepository cRepo;

    @Override
    public Client findClientByEmail(String mail) {
        return cRepo.findClientByEmail(mail);
    }
}
