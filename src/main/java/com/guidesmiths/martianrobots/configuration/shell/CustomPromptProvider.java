package com.guidesmiths.martianrobots.configuration.shell;

import com.guidesmiths.martianrobots.modules.shell.multiexecution.MultiStepExecutionService;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import org.jline.utils.AttributedString;

@Component
public class CustomPromptProvider implements PromptProvider {
    @Autowired
    private MultiStepExecutionService multiStepExecutionService;

    @Override
    public AttributedString getPrompt() {
        return multiStepExecutionService.getMultiStepExecutionInProcess()?new AttributedString(""):new AttributedString("martianrobots$>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
    }
}
