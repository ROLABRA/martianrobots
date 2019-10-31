package com.guidesmiths.martianrobots.configuration;

import org.jline.utils.AttributedStyle;
import org.springframework.context.event.EventListener;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import org.jline.utils.AttributedString;

@Component
public class MultiStepExecutionProcessor implements PromptProvider {
    private boolean multiStepExecutionInProcess = false;

    public void setMultiStepExecutionInProcess(boolean multiStepExecutionInProcess) {
        this.multiStepExecutionInProcess = multiStepExecutionInProcess;
    }

    @Override
    public AttributedString getPrompt() {

        return multiStepExecutionInProcess?new AttributedString(""):new AttributedString("martianrobots$>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
        /*if (connection != null) {
            return new AttributedString(connection.getHost() + ":>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
        }
        else {
            return new AttributedString("server-unknown:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
        }*/
    }

    /*@EventListener
    public void handle(/*ConnectionUpdatedEvent event*//*) {
        //this.connection = event.getConnectionDetails();
        System.out.println("wiii");
    }*/
}
