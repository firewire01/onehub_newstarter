package com.example.new_starter.config;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @Builder
public class ApiError {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;
}