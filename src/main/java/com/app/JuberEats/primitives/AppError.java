package com.app.JuberEats.primitives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    public static AppError None = new AppError("", "", new HashMap<>());

    private String code;
    private String message;
    private Map<String, String> validationErrors;
}
