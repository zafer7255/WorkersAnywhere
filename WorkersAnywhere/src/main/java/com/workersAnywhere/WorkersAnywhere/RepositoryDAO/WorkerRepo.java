package com.workersAnywhere.WorkersAnywhere.RepositoryDAO;

import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, String> {
    Worker findByUsername(String username);
}
