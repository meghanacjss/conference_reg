package com.example.conference_reg.controller;

import com.example.conference_reg.model.OwnerModel;
import com.example.conference_reg.service.OwnerService;
import com.example.conference_reg.service.serviceinterface.OwnerInter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {

    @Autowired
    private OwnerInter ownerService;

    @PostMapping("/register")
    public ResponseEntity<OwnerModel> registerOwner(@Valid @RequestBody OwnerModel ownerModel) {
        OwnerModel registeredOwner = ownerService.registerOwner(ownerModel);
        if (registeredOwner != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredOwner);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<OwnerModel> login(@RequestBody OwnerModel ownerModel) {
        try {
            String username = ownerModel.getUsername();
            String password = ownerModel.getPassword();
            OwnerModel loggedInOwner = ownerService.login(username, password);
            if (loggedInOwner != null) {
                return ResponseEntity.ok(loggedInOwner);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
