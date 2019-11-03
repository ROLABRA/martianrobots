package com.guidesmiths.martianrobots.util.constraints;

public class Constraints {
    // GREETING
    public static final String GREETING = "\nWelcome to %s!. This is your martianrobots console. Type \"help\" if you need some tips to use it.\n\n";

    // COMMANDS
    public static final String START_SIMULATION = "start_simulation";
    public static final String ECHO = "echo";
    public static final String INTERACTIVE_OUTPUT = "interactive_output";
    public static final String FINISH_SIMULATION_COMMAND = "end";

    // VALIDATORS
    public static final int MAX_COORDINATE_VALUE = 50;
    public static final int MIN_COORDINATE_VALUE = 0;
    public static final String BAD_COORDINATES_MSG = "Coordintes must be greater than " + MIN_COORDINATE_VALUE + " and less than " + MAX_COORDINATE_VALUE;
    public static final int MAX_MOVEMENT_INSTRUCTION_LENGTH = 100;
    public static final int MIN_MOVEMENT_INSTRUCTION_LENGTH = 0;
    public static final String BAD_MOVEMENT_INSTRUCTION_LENGHT = "Movements instruction length must be greater than " + MIN_MOVEMENT_INSTRUCTION_LENGTH + " and less than " + MAX_MOVEMENT_INSTRUCTION_LENGTH;

    public static final String NOT_CORRECT_PARAMETERS_COUNT = "Invalid parameters count. Required: %d and %d were found";

    //PREFERENCES KEYS
    public static final String PREFERENCE_INTERACTIVE_OUTPUT_KEY = "INTERACTIVE_MODE";
    public static final String PREFERENCE_INTERACTIVE_OUTPUT_DEFAULT_VALUE = "false";
}
