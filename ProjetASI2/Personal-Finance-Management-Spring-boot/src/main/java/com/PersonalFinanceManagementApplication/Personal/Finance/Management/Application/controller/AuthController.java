package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.controller;

import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Mapper.UserMapper;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.Model.User;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.LoginResponseDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.ResponseDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.dataTransferObject.UserDto;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.exception.NotValidTokenException;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.AuthService;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation.JwtService;
import com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application.service.implementation.UserInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {

    private AuthService authService;
    private JwtService jwtService;

    private UserInfoService userDetailsService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthService authService,JwtService jwtService, AuthenticationManager authenticationManager
    ,UserInfoService userDetailsService){
        this.authService = authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserDto userDto){
       User user = this.authService.Login(UserMapper.mapToUser(userDto));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        String token;
        if (authentication.isAuthenticated()) {
           token = jwtService.generateToken(userDto.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
       return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(token, user,"login successfully"));
    }

    @PostMapping("/singUp")
    public ResponseEntity<UserDto> Register(@RequestBody UserDto userDto){
        System.out.println("====".repeat(20));
        System.out.println("person"+ userDto.getPerson());
        User user = UserMapper.mapToUser(userDto);
        user =this.authService.Register(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
    }

    @GetMapping("/validateToken")
    public ResponseEntity<ResponseDto> validateToken(@RequestHeader("Authorization") String authorizationHeader){
        System.out.println(authorizationHeader);
        String token = null;
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            email = jwtService.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            Boolean validate = this.jwtService.validateToken(token,userDetails);
            if(validate) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Token is valid")) ;
            }else{
                throw new NotValidTokenException("Token is invalid or expired");
            }
        }else{
            throw new NotValidTokenException("Token is empty");
        }
    }

}
