package com.healt_cost_prediction.repository;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healt_cost_prediction.modal.Operator;
@Repository
public interface OperatorRepository extends JpaRepository<Operator, UUID>{
    Page<Operator> findAllByNameContainingIgnoreCase(String search, PageRequest of);

    Operator findByEmail(String email);

}
