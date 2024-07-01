package com.api.school.controller;

import com.api.school.dto.auth.AuthDto;
import com.api.school.dto.auth.LoginDto;
import com.api.school.dto.auth.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping(value = "login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto login){
        return ResponseEntity.ok(new AuthDto("token"));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody RegisterDto dto){
        return ResponseEntity.ok(new AuthDto("token"));
    }

}
