package com.workersAnywhere.WorkersAnywhere.Services.WorkerServices;

import com.workersAnywhere.WorkersAnywhere.Models.Customer;
import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Models.WorkerAndUser;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.CustomerRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerAndUserRepo;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerGetService {

    @Autowired
    WorkerRepo workerRepo;
    @Autowired
    WorkerAndUserRepo workerAndUserRepo;
    @Autowired
    CustomerRepo customerRepo;

    public Worker GetWorkerDetail(String username)
    {
        for (Worker w : workerRepo.findAll())
        {
            if (w.getUsername().equals(username))
            {
                return w;
            }
        }
        return new Worker();
    }

    public List<Worker> GetAllWorkerOfThisState(String state) {
        List<Worker> workers = new ArrayList<>();
        for (Worker w : workerRepo.findAll())
        {
            if (w.getAllTheStateWhereWork().contains(state))
            {
                workers.add(w);
            }
        }
            return workers;
    }

    public List<Worker> getAllWorkerWithWork(String work) {
        List<Worker> workers = new ArrayList<>();
        for (Worker w : workerRepo.findAll())
        {
            if (w.getWork().equals(work)){
                workers.add(w);
            }
        }
        return workers;
    }

    public List<Worker> getAllWorker() {
        List<Worker> workers = new ArrayList<>();
        for (Worker w: workerRepo.findAll())
        {
            workers.add(w);
        }
        return  workers;
    }

    public Worker getWorkerByUsername(String username) {
        Worker worker = new Worker();
        for (Worker w : workerRepo.findAll()){
            if (w.getUsername().equals(username)){
                return w;
            }
        }
        return worker;
    }

    public List<Customer> getAllIntrestedCustomer(String username) {
        List<Customer> customers = new ArrayList<>();
        for (WorkerAndUser w : workerAndUserRepo.findAll()){
            String temp = w.getWorkerUsername();
            if (username.equals(temp)){
                WorkerAndUser workerAndUser = workerAndUserRepo.findByWorkerUsername(username);
                String customerUsername = workerAndUser.getCustomerUsername();
                Customer customer = new Customer();
                customer = customerRepo.findByUsername(customerUsername);
                customer.setPassword("NOT SHOWING");
                customers.add(customer);
            }
        }
        System.out.println(customers);
        return customers;
    }
}
