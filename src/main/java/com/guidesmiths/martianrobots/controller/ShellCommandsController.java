package com.guidesmiths.martianrobots.controller;

import com.guidesmiths.martianrobots.configuration.MultiStepExecutionProcessor;
import com.guidesmiths.martianrobots.model.MultiCommandResult;
import com.guidesmiths.martianrobots.service.RobotBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.Scanner;

@ShellComponent
public class ShellCommandsController {
    @Autowired
    private RobotBehaviorService robotBehaviorService;
    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private Scanner scanner;

    @Autowired
    private MultiStepExecutionProcessor multiStepExecutionProcessor;

    @ShellMethod(value = "Starts martian robot simulation. Type \"exit\" to finish execution.")
    public MultiCommandResult start_simulation(
    ) {
        multiStepExecutionProcessor.setMultiStepExecutionInProcess(true);
        //This has a custom command handler as it's a multi-command utility

        /*try {
            //String a = System.console().readLine();
            //String b = System.console().readLine();
            //Scanner in = new Scanner(System.in);
            //while(!scanner.hasNextLine());
            //String a = scanner.nextLine();
            String a = System.console().readLine();
            //while(!scanner.hasNextLine());
            //String b = scanner.nextLine();
            String b = System.console().readLine();


            System.out.println(a + "\n" + b);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return new MultiCommandResult();
    }

    @ShellMethod("Returns the parameter provided. i.e: \n\t\t\t$>echo \"Hello there\"\n\t\t\tHello there")
    public String echo(
            @ShellOption String text
    ) {

        return text;
    }
}
