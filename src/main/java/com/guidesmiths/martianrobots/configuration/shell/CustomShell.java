package com.guidesmiths.martianrobots.configuration.shell;

import com.guidesmiths.martianrobots.service.MultiStepExecutionService;
import com.guidesmiths.martianrobots.service.RobotBehaviorService;
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
            Object result = super.evaluate(input);

            if(result instanceof CommandNotFound){
                String cmd = input.words().stream().collect(Collectors.joining(" ")).trim();

                if(multiStepExecutionService.getMultiStepExecutionInProcess()){
                    robotBehaviorService.processCommand(cmd);
                }else{
                    //TODO: ¿System.out....?
                    System.out.println("Command " + cmd + "not found");
                }
            }

        return "OK";
    }
}
