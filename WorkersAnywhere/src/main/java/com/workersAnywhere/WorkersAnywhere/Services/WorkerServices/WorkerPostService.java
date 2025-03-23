package com.workersAnywhere.WorkersAnywhere.Services.WorkerServices;

import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerPostService {

    @Autowired
    WorkerRepo workerRepo;
    public String updateWorker(Worker worker, String username) {

        for (Worker w : workerRepo.findAll()){
            if (w.getUsername().equals(username)){
                worker.setUsername(w.getUsername());
                worker.setPassword(w.getPassword());
                workerRepo.save(worker);
                return "Updated";
            }
        }
        return "Customer Not Found";
    }
}
