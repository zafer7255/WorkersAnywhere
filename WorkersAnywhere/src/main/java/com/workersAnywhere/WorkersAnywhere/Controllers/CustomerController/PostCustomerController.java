package com.workersAnywhere.WorkersAnywhere.Controllers.CustomerController;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Services.CustomerService.CustomerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class PostCustomerController {

    @Autowired
    CustomerPostService customerPostService;

    @PostMapping("/postintrestedworker/{workerUsername}")
    public String PostIntrestedWorker(@PathVariable String workerUsername)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerUsername = authentication.getName();
        String result = customerPostService.SaveIntrestedWorker(customerUsername,workerUsername);
        if (result.equals("Saved!!")){
            return result;
        } else {
            return "Error";
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> UpdateCustomer(@RequestBody Customer customer)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String result = customerPostService.updateCustomer(customer,username);
        if (result.equals("Updated")){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
