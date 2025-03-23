package com.workersAnywhere.WorkersAnywhere.RepositoryDAO;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {
    Admin findByUsername(String username);
}
