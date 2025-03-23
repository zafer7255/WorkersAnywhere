package com.workersAnywhere.WorkersAnywhere.Services.UserServices;

import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepo usersRepo;

    public boolean RegisterUser(String username , String password , String role)
    {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        usersRepo.save(user);
        return true;
    }
}
