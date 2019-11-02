package com.guidesmiths.martianrobots.controller;

import com.guidesmiths.martianrobots.service.MultiStepExecutionService;
import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import com.guidesmiths.martianrobots.util.validators.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.annotation.PostConstruct;

@ShellComponent
public class ShellCommandsController {
    @Autowired
    private RobotBehaviorService robotBehaviorService;
    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;

    @PostConstruct
    private void init(){
        System.out.print(Constraints.GREETING);
    }

    @ShellMethod(key = Constraints.START_SIMULATION, value = "Starts martian robot simulation. Type 'Ctrl'+'C' to finish execution (and continue executing the console) or \"exit\" or \"quit\" to exit console.")
    public void start_simulation(
    ) {
        robotBehaviorService.reset();
        multiStepExecutionService.setMultiStepExecutionInProcess(true);

        return;
    }

    @ShellMethod(key = Constraints.ECHO, value = "Returns the parameter provided. i.e: \n\t\t\t$>echo \"Hello there\"\n\t\t\tHello there")
    public String echo(
            @ShellOption String text
    ) {

        return text;
    }
}
