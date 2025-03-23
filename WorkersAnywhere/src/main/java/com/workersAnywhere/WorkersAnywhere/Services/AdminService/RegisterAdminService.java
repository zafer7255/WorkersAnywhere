package com.workersAnywhere.WorkersAnywhere.Services.AdminService;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.AdminRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterAdminService {
    private String role = "ADMIN";
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserService userService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String RegisterAdmin(Admin admin) {

        for (Users u : usersRepo.findAll())
        {
            if (u.getUsername().equals(admin.getUsername()))
            {
                return "Username already exist try new one";
            }
        }
        admin.setPassword(encoder.encode(admin.getPassword()));
        if (userService.RegisterUser(admin.getUsername(),admin.getPassword(),role))
        {
            adminRepo.save(admin);
            return "Registered !!";
        }
        else
        {
            return "Error to saving in User";
        }
    }
}
