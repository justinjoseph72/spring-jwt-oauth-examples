package com.justin.app.demooauthcloudclient.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String username;
    private String password;
}
