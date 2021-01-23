package com.ffg.medha.model;

import lombok.Data;

@Data
public class UserDetails {
    String email;
    String password;
    String oldPassword;
    String newPassword;
}
