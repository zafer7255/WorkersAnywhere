package com.workersAnywhere.WorkersAnywhere.Services.AdminService;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.AdminRepo;
import com.workersAnywhere.WorkersAnywhere.Services.CustomerService.CustomerGetService;
import com.workersAnywhere.WorkersAnywhere.Services.WorkerServices.WorkerGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminGetService {

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    CustomerGetService customerGetService;
    @Autowired
    WorkerGetService workerGetService;

    public Admin GetAdminDetail(String username)
    {
        for (Admin a : adminRepo.findAll())
        {
            if (a.getUsername().equals(username))
            {
                return a;
            }
        }
        return new Admin();
    }

    public List<Customer> getAllCustomer() {

        List<Customer> customers = customerGetService.getAllCustomer();
        return customers;
    }

    public List<Worker> getAllWorkers() {
        List<Worker> workers = workerGetService.getAllWorker();
        return workers;
    }

    public Customer getCustomerByUsername(String username) {
        Customer customer = customerGetService.getCustomerByUsername(username);
        return customer;
    }

    public Worker getWorkerByUsername(String username) {
        Worker worker = workerGetService.getWorkerByUsername(username);
        return worker;
    }
}
