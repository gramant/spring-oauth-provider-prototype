package com.onefactor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created on 07.03.18.
 */
@AllArgsConstructor
@Builder
@Getter
public class User {
    private long id;
    private String name;
    private String password;
    private boolean isEnabled;
    private List<String> roles;
    private Long customerId;
}
