package com.workersAnywhere.WorkersAnywhere.Controllers.AdminController;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.Services.AdminService.RegisterAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AdminRegisterController {

    @Autowired
    RegisterAdminService registerAdminService;
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin)
    {
        String result = registerAdminService.RegisterAdmin(admin);
        if (result == "Username already exist try new one")
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        else if (result == "Registered !!")
            return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
