package org.webshop.to;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserTO {
    private String lastName;
    private String firstName;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
