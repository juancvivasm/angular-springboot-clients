package com.example.backend.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Entity
@Table(name = "clients")
public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no debe estar vacío")
    @Size(min = 3, max = 12, message = "el tamaño debe estar entre 3 y 12")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "no debe estar vacío")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "no debe estar vacío")
    @Email(message = "debe ser una dirección de correo electrónico con formato correcto")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist(){
        log.info("PrePersist method called.");
        createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
