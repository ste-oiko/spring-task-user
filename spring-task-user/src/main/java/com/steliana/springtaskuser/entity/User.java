package com.steliana.springtaskuser.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Setter
@EqualsAndHashCode
@Entity
@Table(name = "user2")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(unique = true)
    public UUID id;

    @Column(name = "first_name")
    private String firstname;
    @Column(name = "surname")
    private String surname;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "personal_id")
    private String personalID;
    @Column(name = "creation_date")
    private String creationDate;

    public void setCreationDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.creationDate = currentDate.format(formatter);
    }

}

