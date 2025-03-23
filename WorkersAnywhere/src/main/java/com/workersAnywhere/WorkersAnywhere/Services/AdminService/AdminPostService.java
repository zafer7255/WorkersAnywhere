package com.workersAnywhere.WorkersAnywhere.Services.AdminService;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPostService {

    @Autowired
    AdminRepo adminRepo;

    public String updateAdmin(Admin admin, String username) {

        for (Admin a : adminRepo.findAll()){
            if (a.getUsername().equals(username)){
                admin.setUsername(a.getUsername());
                admin.setPassword(a.getPassword());
                adminRepo.save(admin);
                return "Updated";
            }
        }
        return "Customer Not Found";
    }
}
