package com.workersAnywhere.WorkersAnywhere.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin {
    @Id
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNo;
}
