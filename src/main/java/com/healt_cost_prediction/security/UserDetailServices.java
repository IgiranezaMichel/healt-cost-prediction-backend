package com.healt_cost_prediction.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healt_cost_prediction.modal.Operator;
import com.healt_cost_prediction.services.OperatorService;

@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired private OperatorService operatorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator accountHolder = operatorService.findByEmail(username);
        if (accountHolder == null)  
            throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");
        return new UserDetailPrinciple(accountHolder);
    }
}