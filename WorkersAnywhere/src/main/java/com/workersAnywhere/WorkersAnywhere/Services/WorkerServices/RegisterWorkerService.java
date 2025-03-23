package com.workersAnywhere.WorkersAnywhere.Services.WorkerServices;

import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerRepo;
import com.workersAnywhere.WorkersAnywhere.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterWorkerService {

    private final String role = "WORKER";
    @Autowired
    WorkerRepo workerRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserService userService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String RegisterWorker(Worker worker)
    {
        for (Users u : usersRepo.findAll())
        {
            if (u.getUsername().equals(worker.getUsername()))
            {
                return "Username already exist try new one";
            }
        }
        worker.setPassword(encoder.encode(worker.getPassword()));
        if (userService.RegisterUser(worker.getUsername() , worker.getPassword(),role))
        {
            workerRepo.save(worker);
            System.out.println(workerRepo.findByUsername(worker.getUsername()));
            return "Registered !!";
        }
        else {
            return "Error to saving in User";
        }
    }

}
