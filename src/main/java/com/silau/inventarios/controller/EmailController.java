package com.silau.inventarios.controller;

import com.silau.inventarios.dto.EmailDTO;
import com.silau.inventarios.responses.ResponseHandler;
import com.silau.inventarios.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/")
    public ResponseEntity<Object> findAll(@RequestBody EmailDTO emailDTO) {
        try {
             EmailDTO result = emailService.sendEmails(emailDTO);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
