package com.guidesmiths.martianrobots.util.validators;

public class Constraints {
    // GREETING
    public static final String GREETING = "Welcome to martianrobots console. Type \"help\" if you need tips to use it.\n\n";

    // COMMANDS
    public static final String START_SIMULATION = "start_simulation";
    public static final String ECHO = "echo";

    // VALIDATORS
    public static final int MAX_COORDINATE_VALUE = 50;
    public static final int MIN_COORDINATE_VALUE = 0;
    public static final String BAD_COORDINATES_MSG = "Coordintes must be greater than " + MIN_COORDINATE_VALUE + " and less than " + MAX_COORDINATE_VALUE;

    public static final String NOT_CORRECT_PARAMETERS_COUNT = "Invalid parameters count. Required: %d and %d were found";
}
