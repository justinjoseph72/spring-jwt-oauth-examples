package com.justin.app.demooauthcloudclient.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cities implements Serializable {
    private Long id;
    private String name;
}
