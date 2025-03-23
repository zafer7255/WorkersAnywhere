package com.workersAnywhere.WorkersAnywhere.Controllers.AdminController;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Services.AdminService.AdminGetService;
import org.apache.coyote.Response;
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
@RequestMapping("/admin")
public class GetAdminController {

    @Autowired
    AdminGetService adminGetService;

    @GetMapping("/detail")
    public ResponseEntity<?> Hello()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Admin admin = adminGetService.GetAdminDetail(username);
        if (admin.getUsername() != null)
        {
            return new ResponseEntity<>(admin, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>("Admin not found ",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getallcustomer")
    public ResponseEntity<?> GetAllCustomer()
    {
        List<Customer> customers = adminGetService.getAllCustomer();
        if (!customers.isEmpty()){
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customers Not Found",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("getallworker")
    public ResponseEntity<?> GetAllWorker()
    {
         List<Worker> workers = adminGetService.getAllWorkers();
        if (!workers.isEmpty()){
            return new ResponseEntity<>(workers,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Workers Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcustomerbyusername/{username}")
    public ResponseEntity<?> GetCustomer(@PathVariable String username)
    {
      Customer customer = adminGetService.getCustomerByUsername(username);
      if (customer.getUsername() == username){
          return new ResponseEntity<>(customer,HttpStatus.OK);
      } else {
          return new ResponseEntity<>("Customer Not Found" , HttpStatus.NOT_FOUND);
      }
    }
    @GetMapping("/getworkerbyusername/{username}")
    public ResponseEntity<?> GetWorker(@PathVariable String username)
    {
        Worker worker = adminGetService.getWorkerByUsername(username);
        if (worker.getUsername() == username){
            return new ResponseEntity<>(worker,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Worker Not Found" , HttpStatus.NOT_FOUND);
        }
    }
}
