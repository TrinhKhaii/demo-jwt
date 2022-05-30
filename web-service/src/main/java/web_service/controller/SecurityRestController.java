package web_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import web_service.model.Account;
import web_service.payload.request.LoginRequest;
import web_service.payload.response.JwtResponse;
import web_service.security.JwtUtility;
import web_service.service.IAccountService;
import web_service.service.impl.AccountDetailsImpl;
import web_service.service.impl.EmailSendService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 11:43
*/

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private EmailSendService emailSendService;




    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // AuthenticationManager sử dụng DaoAuthenticationProvider cùng với
        // UserDetailsService & PasswordEncoder validate instance của UsernamePasswordAuthenticationToken.
        // Nếu thành công, AuthenticationManager trả về một đối tượng Authentication
        // đầy đủ thông tin (bao gồm cả các quyền).
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = accountDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println("authenticateUser() at SecurityRestController in controller package.");
        return new ResponseEntity<>(new JwtResponse(jwt, accountDetails.getId(), accountDetails.getUsername(), roles), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-username")
    public ResponseEntity<Account> findByUserName(@RequestParam String username) {
        Account account = iAccountService.findByUserName(username);
        if (account != null) {
            String code = iAccountService.setVetificationCode(account);
            emailSendService.setMail(account.getEmail(), "Xác nhận", "Mã xác nhận " + code);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/check-code")
    public ResponseEntity<Boolean> checkCode(@RequestParam("username") String username, @RequestParam("code") String code) {
        Account account = iAccountService.findByUserName(username);
        if (account != null) {
            if (account.getVerificationCode().equals(code)) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
