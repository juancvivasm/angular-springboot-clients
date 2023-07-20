package com.example.backend.api.repository;

import com.example.backend.api.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientRepository extends CrudRepository<Client, Long> {
}
