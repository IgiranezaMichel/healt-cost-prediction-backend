package com.healt_cost_prediction.dto;

import java.util.Base64;
import java.util.UUID;

import com.healt_cost_prediction.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperatorDTO {
private String id;
private String name;
private String profile;
private String gender;
private String email;
private Role role;
public OperatorDTO(UUID id, String name, byte[] profile, String gender, String email, Role role) {
    this.id = id.toString();
    this.name = name;
    this.profile = "data:image/png;base64,"+Base64.getEncoder().encodeToString(profile);
    this.gender = gender;
    this.email = email;
    this.role = role;
}

}
