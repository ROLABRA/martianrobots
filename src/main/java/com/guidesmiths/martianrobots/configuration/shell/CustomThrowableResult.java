package com.guidesmiths.martianrobots.configuration.shell;

import com.guidesmiths.martianrobots.model.MultiCommandResult;
import com.guidesmiths.martianrobots.service.MultiStepExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.ResultHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomThrowableResult implements ResultHandler<Throwable> {
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;

    @Override
    public void handleResult(Throwable result) {
        if(result instanceof ExitRequest){
            if(multiStepExecutionService.getMultiStepExecutionInProcess()) {
                multiStepExecutionService.setMultiStepExecutionInProcess(false);
                // Here it is necessary to add a bit of delay because otherwise the program may leave involuntarily when pressing "Ctrl+C"
                try {
                    Thread.sleep(350L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.exit(0);
            }
        }else{
            //TODO: LOG
        }
    }
}