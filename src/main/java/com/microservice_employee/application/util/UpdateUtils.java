package com.microservice_employee.application.util;

public final class UpdateUtils {

    private UpdateUtils() {
    }

    public static <T> T valueOrDefault(T newValue, T existingValue) {
        return newValue != null ? newValue : existingValue;
    }
}