package com.guidesmiths.martianrobots.configuration.shell;

import com.guidesmiths.martianrobots.modules.robot.RobotBehaviorService;
import com.guidesmiths.martianrobots.modules.shell.multiexecution.MultiStepExecutionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.ResultHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomThrowableResult implements ResultHandler<Throwable> {
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;
    @Autowired
    private RobotBehaviorService robotBehaviorService;
    private final Logger LOG = Logger.getLogger(CustomThrowableResult.class);

    @Override
    public void handleResult(Throwable result) {
        if(result instanceof ExitRequest){
            if(multiStepExecutionService.getMultiStepExecutionInProcess()) {
                multiStepExecutionService.setMultiStepExecutionInProcess(false);
                // Here it is necessary to add a bit of delay because otherwise the program may leave involuntarily when pressing "Ctrl+C"
                try {
                    Thread.sleep(350L);
                } catch (InterruptedException e) {
                    LOG.debug("Sleep interrupted", e);
                }
            }else{
                System.exit(0);
            }
        }else{
            LOG.debug("Unrecognized throwable", result);
        }
    }
}