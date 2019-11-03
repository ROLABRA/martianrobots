package com.guidesmiths.martianrobots.configuration.shell;

import com.guidesmiths.martianrobots.modules.shell.multiexecution.MultiStepExecutionService;
import com.guidesmiths.martianrobots.modules.robot.RobotBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.*;

import java.util.stream.Collectors;

public class CustomShell extends Shell {
    @Autowired
    public RobotBehaviorService robotBehaviorService;
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;

    public CustomShell(ResultHandler resultHandler) {
        super(resultHandler);
    }

    @Override
    public Object evaluate(Input input) {
        String cmd = input.words().stream().collect(Collectors.joining(" ")).trim();
        if(multiStepExecutionService.getMultiStepExecutionInProcess() && cmd.equalsIgnoreCase("script")){
            return "Command not allowed while simulation. Press 'end' to finish simulation.";
        }
        Object result = super.evaluate(input);

        if(result instanceof CommandNotFound){

            if(multiStepExecutionService.getMultiStepExecutionInProcess()){
                return robotBehaviorService.processCommand(cmd);
            }else{
                return("Command \"" + cmd + "\" not found. You can enter \"help\" command to get more information.");
            }
        }

        return result;
    }
}
