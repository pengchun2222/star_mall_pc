package com.ququ.star.common.model;

import lombok.Data;

@Data
public class LoginVal extends JwtInformation{

    private String userId;

    private String username;

    private String[] authorities;
}