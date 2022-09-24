package com.example.cartapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class UserData {
    int userId;
    String firstName;
    String lastName;
    String address;

    String email;
    LocalDate DOB;
    String password;
}
