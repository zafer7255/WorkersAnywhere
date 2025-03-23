package com.workersAnywhere.WorkersAnywhere.Services.CustomerService;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.CustomerRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import com.workersAnywhere.WorkersAnywhere.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerService {

    private final String role = "CUSTOMER";
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String RegisterCustomer(Customer customer) {

        for (Users u : usersRepo.findAll())
        {
            if (u.getUsername().equals(customer.getUsername()))
            {
                return "Username already exist try new one";
            }
        }
        customer.setPassword(encoder.encode(customer.getPassword()));
        if (userService.RegisterUser(customer.getUsername(),customer.getPassword(),role))
        {
            customerRepo.save(customer);
            return "Registered !!";
        }
        else
        {
            return "Error to saving in User";
        }
    }
}
