package com.workersAnywhere.WorkersAnywhere.RepositoryDAO;


import com.workersAnywhere.WorkersAnywhere.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo extends JpaRepository<Users , String> {

    Users findByUsername(String username);

}
