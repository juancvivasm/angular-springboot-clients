package com.example.backend.api.controllers;

import com.example.backend.api.entity.Client;
import com.example.backend.api.exceptions.MyResourceNotFoundException;
import com.example.backend.api.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    IClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) throws MyResourceNotFoundException {
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()) {
            throw new MyResourceNotFoundException("Client id: " + id + " not found");
        }
        return ResponseEntity.ok(clientOptional.get());
    }

    @PostMapping("/clients")
    public ResponseEntity<?> saveClient(@Valid @RequestBody Client mClient, BindingResult result) {
        Client client = null;
        Map<String, Object> data = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream().map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.toList());
            data.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(data, HttpStatus.BAD_REQUEST);
        }

        try {
            client = clientService.save(mClient);
        } catch (DataAccessException e) {
            data.put("message", "Error saving the client in the database.");
            data.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        data.put("message", "The client has been saved successfully.");
        data.put("client", client);
        return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
    }

//    @PutMapping("/clients")
//    public ResponseEntity<Client> updateClient(@RequestBody Client mClient) {
//        Client client = clientService.save(mClient);
//        return ResponseEntity.ok(client);
//    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") long id) throws MyResourceNotFoundException {
        Map<String, Object> data = new HashMap<>();

        if (!clientService.existsById(id)) {
            throw new MyResourceNotFoundException("Client id: " + id + " not found");
        }

        clientService.delete(id);
        data.put("message", "Client successfully removed");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
