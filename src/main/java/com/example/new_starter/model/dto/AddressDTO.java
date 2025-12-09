package com.example.new_starter.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private String state;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Postal code cannot be blank")
    private String postalCode;
}