package com.autogarage.eindopdracht.Controllers;

import com.autogarage.eindopdracht.DTO.EmployeeDTO;
import com.autogarage.eindopdracht.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@RequestBody EmployeeDTO employeeDTO) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(employeeDTO.getUsername(), employeeDTO.getPassword());
        Authentication auth = authManager.authenticate(up);

        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(ud);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body("Token generated");
    }

}
