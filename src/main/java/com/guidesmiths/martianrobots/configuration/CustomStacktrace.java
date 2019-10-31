package com.guidesmiths.martianrobots.configuration;

import org.jline.terminal.Terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.result.ThrowableResultHandler;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * A command to display the full stacktrace when an error occurs.
 */
/*
@ShellComponent
public class CustomStacktrace {

    public interface Command {}

    @Autowired @Lazy
    private Terminal terminal;

    @Autowired
    private CustomThrowableResultHandler throwableResultHandler;


    @ShellMethod(key = ThrowableResultHandler.DETAILS_COMMAND_NAME, value = "Display the full stacktrace of the last error.")
    public void stacktrace() {
        if (throwableResultHandler.getLastError() != null) {
            throwableResultHandler.getLastError().printStackTrace(terminal.writer());
        }
    }
}
*/