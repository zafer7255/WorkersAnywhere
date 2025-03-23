package com.workersAnywhere.WorkersAnywhere.Services.CustomerService;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.WorkerAndUser;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.CustomerRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerAndUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/customer")
public class CustomerPostService {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    WorkerAndUserRepo workerAndUserRepo;

    public String updateCustomer(Customer customer, String username) {

        for (Customer c : customerRepo.findAll())
        {
            if (c.getUsername().equals(username)){
                customer.setPassword(c.getPassword());
                customer.setUsername(c.getUsername());
                customerRepo.save(customer);
                return "Updated";
            }
        }
        return "Customer Not Found";
    }

    public String SaveIntrestedWorker(String customerUsername, String workerUsername) {
        WorkerAndUser workerAndUser = new WorkerAndUser();
        workerAndUser.setCustomerUsername(customerUsername);
        workerAndUser.setWorkerUsername(workerUsername);
        workerAndUserRepo.save(workerAndUser);
        return "Saved!!";
    }
}
