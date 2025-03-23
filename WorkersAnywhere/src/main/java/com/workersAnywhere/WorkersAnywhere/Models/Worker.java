package com.workersAnywhere.WorkersAnywhere.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worker {
    @Id
    private String username;
    private String password;
    private String name;
    private String address;
    private String phoneNo;
    private String work;
    private List<String> allTheStateWhereWork = new ArrayList<>();
    private int chargePerDay;
    private String overAllRating;
    private String Email;
}
