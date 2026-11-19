package com.microservice_employee.infrastructure.adapter.in.rest.constant;

public final class RequestMessages {

    private RequestMessages(){
    }

    public static final String FIRST_NAME_REQUIRED = "The first name is required";
    public static final String LAST_NAME_REQUIRED = "The last name is required";
    public static final String MOTHER_LAST_NAME_REQUIRED = "The mother last name is required";
    public static final String AGE_REQUIRED = "Age is required";
    public static final String AGE_NOT_NEGATIVE = "Age cannot be negative";
    public static final String AGE_LIMIT = "The age cannot exceed 150";
    public static final String GENDER_REQUIRED = "Gender is required";
    public static final String REGEX_GENDER = "M|F|X";
    public static final String GENDER_OPTIONS = "The gender must be M, F, or X";
    public static final String BIRTH_DATE_REQUIRED = "Birth date is required";
    public static final String BIRTH_DATE_RESTRICTION = "The date of birth must be earlier than today";
    public static final String PATTERN_DATE = "dd-MM-yyyy";
    public static final String POSITION_REQUIRED = "The position is required";
    public static final String STATUS_REQUIRED = "The status is required";

}