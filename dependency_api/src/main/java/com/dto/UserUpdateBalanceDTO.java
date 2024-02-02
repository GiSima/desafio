package com.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateBalanceDTO(
        @NotNull(message = "User Id necessary for deposit")
        Long id,
        double deposit){


}
