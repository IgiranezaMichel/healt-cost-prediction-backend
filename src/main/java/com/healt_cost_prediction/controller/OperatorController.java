package com.healt_cost_prediction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healt_cost_prediction.dto.OperatorDTO;
import com.healt_cost_prediction.enums.Role;
import com.healt_cost_prediction.generic.Pagination;
import com.healt_cost_prediction.services.OperatorService;
@RestController
@RequestMapping("api/operator")
public class OperatorController {
@Autowired private OperatorService operatorService;
@PostMapping(value="create")
public ResponseEntity<String>createOperator(@RequestBody OperatorDTO OperatorDTO,@RequestParam String password){
    return operatorService.create(OperatorDTO,password);
}
@PostMapping(value="update")
public ResponseEntity<String>updateOperator(@RequestBody OperatorDTO productDTO,@RequestParam String password){
    return operatorService.update(productDTO,password);
}
@GetMapping(value="all")
public Pagination<OperatorDTO>operatorList(@RequestParam(name="search")String search,@RequestParam(defaultValue = "0")int pageNumber,@RequestParam(defaultValue = "10")int pageSize){
    return operatorService.userRoleList(search,pageNumber,pageSize);
}
@DeleteMapping(value="delete")
public ResponseEntity<String>deleteOperator(@RequestParam()String operatorId){
    return operatorService.deleteOperator(operatorId);
}
@PostMapping(value="change/role")
public ResponseEntity<String>changeOperatorRole(@RequestParam()String operatorId,@RequestParam() Role role){
    return operatorService.changeOperatorRole(operatorId,role);
}
}
