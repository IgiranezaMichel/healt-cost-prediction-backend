package com.healt_cost_prediction.modal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.healt_cost_prediction.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operator {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
@Lob
private byte[] profile;
private String gender;
@Column(unique = true)
private String email;
private String passord;
@Enumerated(EnumType.STRING)
private Role role;
private LocalDateTime timeStamp;
public Operator(UUID id, String name, byte[] profile, String gender, String email, String passord, Role role) {
    this.id = id;
    this.name = name;
    this.profile = profile;
    this.gender = gender;
    this.email = email;
    this.passord = passord;
    this.role = role;
}

public Operator(String name, byte[] profile, String gender, String email, String passord, Role role) {
    this.name = name;
    this.profile = profile;
    this.gender = gender;
    this.email = email;
    this.passord = passord;
    this.role = role;
}
}
