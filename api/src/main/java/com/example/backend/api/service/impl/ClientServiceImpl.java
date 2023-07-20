package com.example.backend.api.service.impl;

import com.example.backend.api.entity.Client;
import com.example.backend.api.exceptions.MyResourceNotFoundException;
import com.example.backend.api.repository.IClientRepository;
import com.example.backend.api.service.IClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    @Autowired
    IClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        List<Client> clientList = (List<Client>) clientRepository.findAll();
        return clientList;
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient;
    }

    @Override
    public Client save(Client mClient) {
        if (mClient.getId() > 0) {
            return clientRepository.findById(mClient.getId())
                    .map(clientToBeUpdated -> {
                        clientToBeUpdated.setName(mClient.getName());
                        clientToBeUpdated.setLastName(mClient.getLastName());
                        clientToBeUpdated.setEmail(mClient.getEmail());
                        //clientToBeUpdated.setCreateAt(mClient.getCreateAt());
                        return clientRepository.save(clientToBeUpdated);
                    }).orElseThrow(() -> new MyResourceNotFoundException("Client id: " + mClient.getId() + " not found"));
        }
        
        return clientRepository.save(mClient);
    }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
