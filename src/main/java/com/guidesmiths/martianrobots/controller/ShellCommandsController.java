package com.guidesmiths.martianrobots.controller;

import com.guidesmiths.martianrobots.modules.shell.multiexecution.MultiStepExecutionService;
import com.guidesmiths.martianrobots.modules.robot.RobotBehaviorService;
import com.guidesmiths.martianrobots.modules.shell.preferences.ShellPreferencesService;
import com.guidesmiths.martianrobots.util.constraints.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.annotation.PostConstruct;

import static com.guidesmiths.martianrobots.util.constraints.Constraints.FINISH_SIMULATION_COMMAND;
import static com.guidesmiths.martianrobots.util.constraints.Constraints.PREFERENCE_INTERACTIVE_OUTPUT_KEY;

@ShellComponent
public class ShellCommandsController {
    @Autowired
    private RobotBehaviorService robotBehaviorService;
    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;
    @Autowired
    private ShellPreferencesService shellPreferencesService;

    @Value("${planet.name}")
    private String planetName;

    @PostConstruct
    private void init(){
        System.out.print(String.format(Constraints.GREETING, planetName));
    }

    @ShellMethod(key = Constraints.START_SIMULATION, value = "Starts martian robot simulation. Type '" + FINISH_SIMULATION_COMMAND + "' to finish execution (and continue executing the console) or type \"exit\" or \"quit\" to exit console. Info for this process can be found at: https://github.com/guidesmiths/interview-code-challenges/blob/master/java/martian-robots/instructions.md")
    public void start_simulation(
    ) {
        robotBehaviorService.reset();
        multiStepExecutionService.setMultiStepExecutionInProcess(true);

        return;
    }

    @ShellMethod(key = Constraints.INTERACTIVE_OUTPUT, value = "Usage: " + Constraints.INTERACTIVE_OUTPUT + " [on|off|get]. Set the preference of interactive output. When interactive output is set to 'on', the robot position is shown in each iteration. Otherwise the robots final position is shown at the end.")
    public String interactive_output(
            @ShellOption(arity = 1) String activated
    ) {
        switch(activated){
            case "on":
            case "true":
                shellPreferencesService.setPreference(PREFERENCE_INTERACTIVE_OUTPUT_KEY, "true");

                return "Interactive output turned ON";
            case "off":
            case "false":
                shellPreferencesService.setPreference(PREFERENCE_INTERACTIVE_OUTPUT_KEY, "false");

                return "Interactive output turned OFF";
            case "get":
                shellPreferencesService.getPreference(PREFERENCE_INTERACTIVE_OUTPUT_KEY);

                return "Interactive output is currently " + (Boolean.valueOf(shellPreferencesService.getPreference(PREFERENCE_INTERACTIVE_OUTPUT_KEY))?"ON":"OFF");
            default:
                return "Invalid parameter. Usage: " + Constraints.INTERACTIVE_OUTPUT + " [on|off]";
        }
    }

    @ShellMethod(key = Constraints.ECHO, value = "Returns the parameter provided. i.e: \n\t\t\t$>echo \"Hello there\"\n\t\t\tHello there")
    public String echo(
            @ShellOption String text
    ) {

        return text;
    }
}
