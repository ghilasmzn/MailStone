package com.server.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.server.server.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findClientByEmail(String mail);
}
