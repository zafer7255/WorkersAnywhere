package com.workersAnywhere.WorkersAnywhere.RepositoryDAO;

import com.workersAnywhere.WorkersAnywhere.Models.WorkerAndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkerAndUserRepo extends JpaRepository<WorkerAndUser, Integer> {
    WorkerAndUser findByWorkerUsername(String username);
}
