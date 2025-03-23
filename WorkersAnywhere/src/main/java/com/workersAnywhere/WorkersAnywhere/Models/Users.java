package com.workersAnywhere.WorkersAnywhere.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    @Id
    private String username;
    private String password;
    private String role;
}
