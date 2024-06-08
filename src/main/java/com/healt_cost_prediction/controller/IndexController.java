package com.healt_cost_prediction.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healt_cost_prediction.dto.OperatorDTO;
import com.healt_cost_prediction.modal.Operator;
import com.healt_cost_prediction.services.OperatorService;

@RestController
public class IndexController {
@Autowired private OperatorService operatorService;
@RequestMapping("/login")
public Object login(){
    return null;
}
@RequestMapping("/login-success")
public OperatorDTO successLogin(Principal principal){
   Operator operator= operatorService.findByEmail(principal.getName());
   return new OperatorDTO(operator.getId(), operator.getName(), operator.getProfile(), operator.getGender(), operator.getEmail(), operator.getRole());
}
}
