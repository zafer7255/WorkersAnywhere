package com.workersAnywhere.WorkersAnywhere.Services.CustomerService;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Models.WorkerAndUser;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.CustomerRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerAndUserRepo;
import com.workersAnywhere.WorkersAnywhere.Services.WorkerServices.WorkerGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerGetService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    WorkerGetService workerGetService;
    @Autowired
    WorkerAndUserRepo workerAndUserRepo;
    public Customer getCustomerDetail(String username)
    {
        for (Customer c : customerRepo.findAll())
        {
            if (c.getUsername().equals(username)){
                return c;
            }
        }
        return new Customer();
    }

    public List<Worker> getAllWorkerThatCanWorkYourState(String username) {
        Customer customer = customerRepo.findByUsername(username);
        String state = customer.getState();
        List<Worker> workers = new ArrayList<>();
        workers = workerGetService.GetAllWorkerOfThisState(state);
        return workers;
    }

    public List<Worker> getAllWorkerWithWork(String work) {

        List<Worker> workers = workerGetService.getAllWorkerWithWork(work);
        return workers;
    }

    public List<Customer> getAllCustomer() {

        List<Customer> customers = new ArrayList<>();
        for (Customer c: customerRepo.findAll())
        {
            customers.add(c);
        }
        return customers;
    }

    public Customer getCustomerByUsername(String username) {
        Customer customer = new Customer();
        for (Customer c : customerRepo.findAll()){
            if (c.getUsername().equals(username)){
                return c;
            }
        }
        return customer;
    }

}
