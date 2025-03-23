package com.workersAnywhere.WorkersAnywhere.Controllers.WorkerController;


import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Services.WorkerServices.WorkerGetService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class GetWorkerController {

    @Autowired
    WorkerGetService workerGetService;

    @GetMapping("/detail")
    public ResponseEntity<?> GetWorkerDetail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Worker worker = workerGetService.GetWorkerDetail(username);
        if (worker.getUsername() != null)
        {
            return new ResponseEntity<>(worker, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Worker not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getallintrestedcustomer")
    public ResponseEntity<?> GetAllIntrestedCustomer()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Worker Username " + username);
        List<Customer> customers = workerGetService.getAllIntrestedCustomer(username);
        if (!customers.isEmpty()){
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not any customer intrested",HttpStatus.NOT_FOUND);
        }
    }

}
