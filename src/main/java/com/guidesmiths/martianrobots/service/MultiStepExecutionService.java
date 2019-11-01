package com.guidesmiths.martianrobots.service;

import org.springframework.stereotype.Service;

@Service
public class MultiStepExecutionService {
    private boolean multiStepExecutionInProcess = false;

    public void setMultiStepExecutionInProcess(boolean multiStepExecutionInProcess) {
        this.multiStepExecutionInProcess = multiStepExecutionInProcess;
    }

    public boolean getMultiStepExecutionInProcess() {
        return this.multiStepExecutionInProcess;
    }
}
