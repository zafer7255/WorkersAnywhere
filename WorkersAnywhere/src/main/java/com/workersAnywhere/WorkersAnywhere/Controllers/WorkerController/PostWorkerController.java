package com.workersAnywhere.WorkersAnywhere.Controllers.WorkerController;

import com.workersAnywhere.WorkersAnywhere.Models.Worker;
import com.workersAnywhere.WorkersAnywhere.Services.WorkerServices.WorkerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
public class PostWorkerController {

    @Autowired
    WorkerPostService workerPostService;
    @PutMapping("/update")
    public ResponseEntity<String> updateWorker(@RequestBody Worker worker){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String result = workerPostService.updateWorker(worker,username);
        if (result.equals("Updated")){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
