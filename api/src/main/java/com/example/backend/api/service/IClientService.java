package com.example.backend.api.service;

import com.example.backend.api.entity.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    public List<Client> findAll();

    public Optional<Client> findById(Long id);

    public Client save(Client mClient);

    public boolean existsById(Long id);

    public void delete(Long id);

}
