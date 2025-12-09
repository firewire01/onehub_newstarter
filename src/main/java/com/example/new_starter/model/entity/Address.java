package com.example.new_starter.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}