package com.workersAnywhere.WorkersAnywhere.Controllers.CustomerController;


import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Services.CustomerService.RegisterCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class CustomerRegisterController {

    @Autowired
    RegisterCustomerService registerCustomerService;

    @PostMapping("customer")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer)
    {
        String result = registerCustomerService.RegisterCustomer(customer);
        if (result == "Username already exist try new one")
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        else if (result == "Registered !!")
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
