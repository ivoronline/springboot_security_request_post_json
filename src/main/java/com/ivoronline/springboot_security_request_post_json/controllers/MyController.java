package com.ivoronline.springboot_security_request_post_json.controllers;

import com.ivoronline.springboot_security_request_post_json.DTO.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  @Autowired AuthenticationManager authenticationManager;

  //========================================================================
  // AUTHENTICATE
  //========================================================================
  @ResponseBody
  @RequestMapping("/Authenticate")
  public String authenticate(@RequestBody CredentialsDTO credentialsDTO) {

    //CREATE AUTHENTICATION OBJECT (with Entered Username & Password)
    String username = credentialsDTO.username;
    String password = credentialsDTO.password;
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

    //GET    AUTHENTICATION OBJECT (with Authorities)
    authentication = authenticationManager.authenticate(authentication);

    //STORE  AUTHENTICATION OBJECT (into Context)
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //RETURN SOMETHING
    return "User Authenticated";

  }

  //========================================================================
  // HELLO
  //========================================================================
  @ResponseBody
  @Secured("ROLE_USER")
  @RequestMapping("/Hello")
  public String hello() {
    return "Hello from Controller";
  }

}


