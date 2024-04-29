package com.eletronico.pontoapi.adapters.controllers;


import com.eletronico.pontoapi.core.dto.PasswordResetRequestDTO;
import com.eletronico.pontoapi.core.password.PasswordResetRequest;
import com.eletronico.pontoapi.core.password.PasswordResetToken;
import com.eletronico.pontoapi.core.password.PasswordResetTokenService;
import com.eletronico.pontoapi.core.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<PasswordResetToken> passwordReset(@RequestBody PasswordResetRequestDTO passwordResetRequestDTO) {

        return new ResponseEntity<>(passwordResetTokenService.createToken(passwordResetRequestDTO), HttpStatus.OK);
    }
}
