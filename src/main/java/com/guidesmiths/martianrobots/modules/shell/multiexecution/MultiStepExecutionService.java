package com.guidesmiths.martianrobots.modules.shell.multiexecution;

import org.springframework.stereotype.Component;

@Component
public class MultiStepExecutionService {
    private boolean multiStepExecutionInProcess = false;

    public void setMultiStepExecutionInProcess(boolean multiStepExecutionInProcess) {
        this.multiStepExecutionInProcess = multiStepExecutionInProcess;
    }

    public boolean getMultiStepExecutionInProcess() {
        return this.multiStepExecutionInProcess;
    }
}
