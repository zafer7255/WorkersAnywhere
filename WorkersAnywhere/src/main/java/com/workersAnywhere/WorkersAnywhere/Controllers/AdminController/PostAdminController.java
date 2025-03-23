package com.workersAnywhere.WorkersAnywhere.Controllers.AdminController;

import com.workersAnywhere.WorkersAnywhere.Models.Admin;
import com.workersAnywhere.WorkersAnywhere.Services.AdminService.AdminPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PostAdminController {

    @Autowired
    AdminPostService adminPostService;


    @PutMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestBody Admin admin){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String result = adminPostService.updateAdmin(admin,username);
        if (result.equals("Updated")){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
