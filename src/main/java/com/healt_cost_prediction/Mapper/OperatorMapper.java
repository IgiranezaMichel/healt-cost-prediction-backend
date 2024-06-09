package com.healt_cost_prediction.Mapper;
import java.util.function.Function;
import com.healt_cost_prediction.dto.OperatorDTO;
import com.healt_cost_prediction.modal.Operator;

public class OperatorMapper implements Function<Operator,OperatorDTO>{

    @Override
    public OperatorDTO apply(Operator t) {  
      return new OperatorDTO(t.getId(), t.getName(), t.getProfile(), t.getGender(), t.getEmail(), t.getRole());
    }

}
