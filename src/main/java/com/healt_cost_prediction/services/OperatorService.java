package com.healt_cost_prediction.services;

import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healt_cost_prediction.Mapper.OperatorMapper;
import com.healt_cost_prediction.dto.OperatorDTO;
import com.healt_cost_prediction.enums.Role;
import com.healt_cost_prediction.generic.Pagination;
import com.healt_cost_prediction.modal.Operator;
import com.healt_cost_prediction.repository.OperatorRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
@Service
public class OperatorService {
@Autowired private OperatorRepository operatorRepository;
private OperatorMapper operatorMapper=new OperatorMapper();
public ResponseEntity<String> create(OperatorDTO operatorDTO,String password) {
    if(operatorDTO.getName().length()==0)return new ResponseEntity<>("Name  is required",HttpStatus.BAD_REQUEST);
   else if(operatorDTO.getProfile().length()==0)return new ResponseEntity<>("Profile Picture is required",HttpStatus.BAD_REQUEST);
    else if(operatorDTO.getGender().length()==0)return new ResponseEntity<>("Gender is required",HttpStatus.BAD_REQUEST);
    else if(operatorDTO.getEmail().length()==0)return new ResponseEntity<>("Email is required",HttpStatus.BAD_REQUEST);
    else {
   try {
    password=BCrypt.hashpw(password, BCrypt.gensalt());
    Operator operator=operatorRepository.save(new Operator(operatorDTO.getName(), Base64.getDecoder().decode(operatorDTO.getProfile().split("base64,")[1]), operatorDTO.getGender(), operatorDTO.getEmail(), password, operatorDTO.getRole()));
    return new ResponseEntity<>(operator.getName()+" saved successful",HttpStatus.CREATED);
   } catch (Exception e) {
    return new ResponseEntity<>("User account Already exist",HttpStatus.BAD_REQUEST);
   }
    }
}

public ResponseEntity<String> update(OperatorDTO operatorDTO,String password) {
    if(operatorDTO.getName().length()==0)return new ResponseEntity<>("Name  is required",HttpStatus.BAD_REQUEST);
    else if(operatorDTO.getProfile().length()==0)return new ResponseEntity<>("Profile Picture is required",HttpStatus.BAD_REQUEST);
     else if(operatorDTO.getGender().length()==0)return new ResponseEntity<>("Gender is required",HttpStatus.BAD_REQUEST);
     else if(operatorDTO.getEmail().length()==0)return new ResponseEntity<>("Email is required",HttpStatus.BAD_REQUEST);
     else {
     Operator operator=operatorRepository.save(new Operator(UUID.fromString(operatorDTO.getId()),operatorDTO.getName(), Base64.getDecoder().decode(operatorDTO.getProfile().split("base64,")[1]), operatorDTO.getGender(), operatorDTO.getEmail(), password, operatorDTO.getRole()));
     return new ResponseEntity<>(operator.getName()+" updated successful",HttpStatus.CREATED);
     }
}

public Pagination<OperatorDTO> userRoleList(String search, int pageNumber, int pageSize) {
    if(search.length()==0){
        Page<Operator> page = operatorRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new Pagination<>(page.getContent().stream().map(operatorMapper).toList(), page.getNumber(), page.getTotalPages());
    }else{
        Page<Operator> page = operatorRepository.findAllByNameContainingIgnoreCase(search,PageRequest.of(pageNumber, pageSize));
        return new Pagination<>(page.getContent().stream().map(operatorMapper).toList(), page.getNumber(), page.getTotalPages());
    }
}

public ResponseEntity<String> deleteOperator(String operatorId) {
  try {
    Operator operator=operatorRepository.findById(UUID.fromString(operatorId)).orElseThrow();
    operatorRepository.delete(operator);
    return new ResponseEntity<>(operator.getName()+" deleted successful",HttpStatus.OK);
   } catch (Exception e) {
    return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
   }
}

public ResponseEntity<String> changeOperatorRole(String operatorId, Role role) {
    try {
        Operator operator=operatorRepository.findById(UUID.fromString(operatorId)).orElseThrow();
        operator.setRole(role);
        Operator operator1=operatorRepository.save(operator);
        return new ResponseEntity<>(operator1.getName()+" role changed successful",HttpStatus.OK);
       } catch (Exception e) {
        return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
       }
}
public Operator findByEmail(String email){
   return operatorRepository.findByEmail(email);
}
}
