package com.workersAnywhere.WorkersAnywhere.Services;

import com.workersAnywhere.WorkersAnywhere.Models.UserPrincipal;
import com.workersAnywhere.WorkersAnywhere.Models.Users;
import com.workersAnywhere.WorkersAnywhere.RepositoryDAO.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UsersRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if (users == null)
        {
            throw new UsernameNotFoundException("User not found " + username);
        }
        return new UserPrincipal(users);
    }
}
