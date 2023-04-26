package com.steliana.springtaskuser.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "user2")
@NoArgsConstructor
@Getter @Setter
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
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "personalID")
    private String personalID;
    @Column(name = "date")
    private String date;

//    public void setDate() {
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = currentDate.format(formatter);
//        this.date = formattedDate;
//    }


}

