package com.guidesmiths.martianrobots.controller;

import com.guidesmiths.martianrobots.model.MultiCommandResult;
import com.guidesmiths.martianrobots.service.MultiStepExecutionService;
import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommandsController {
    @Autowired
    private RobotBehaviorService robotBehaviorService;
    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;

    @ShellMethod(value = "Starts martian robot simulation. Type \"exit\" to finish execution.")
    public MultiCommandResult start_simulation(
    ) {
        multiStepExecutionService.setMultiStepExecutionInProcess(true);

        return new MultiCommandResult();
    }

    @ShellMethod("Returns the parameter provided. i.e: \n\t\t\t$>echo \"Hello there\"\n\t\t\tHello there")
    public String echo(
            @ShellOption String text
    ) {

        return text;
    }
}
