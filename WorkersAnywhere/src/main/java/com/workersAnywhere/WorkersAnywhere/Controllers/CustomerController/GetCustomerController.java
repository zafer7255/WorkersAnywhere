package com.workersAnywhere.WorkersAnywhere.Controllers.CustomerController;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Services.CustomerService.CustomerGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class GetCustomerController {

    @Autowired
    CustomerGetService customerGetService;
    @GetMapping("/detail")
    public ResponseEntity<?> GetCustomerDetail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerGetService.getCustomerDetail(username);
        if (customer.getUsername() != null)
        {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allworkersworkinyourstate")
    public ResponseEntity<?> GetAllWorkers()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Username is " + username);
        List<Worker> workers = customerGetService.getAllWorkerThatCanWorkYourState(username);
        if (workers.isEmpty()){
            return new ResponseEntity<>("No worker want to work in your state",HttpStatus.OK);
        } else {
            return new ResponseEntity<>(workers,HttpStatus.FOUND);
        }
    }

    @GetMapping("/getworkerwithspecificwork/{work}")
    public ResponseEntity<?> GetWorkers(@PathVariable String work)
    {
         List<Worker> workers = customerGetService.getAllWorkerWithWork(work);
         if (!workers.isEmpty()){
             return new ResponseEntity<>(workers,HttpStatus.OK);
         } else {
             return new ResponseEntity<>("Worker Not Found",HttpStatus.NOT_FOUND);
         }
    }
}
