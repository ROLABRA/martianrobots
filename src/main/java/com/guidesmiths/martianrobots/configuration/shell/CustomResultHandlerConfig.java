package com.guidesmiths.martianrobots.configuration.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.ResultHandler;
import org.springframework.shell.TerminalSizeAware;
import org.springframework.shell.result.*;

@Configuration
public class CustomResultHandlerConfig {

    @Bean
    @Qualifier("main")
    public ResultHandler<?> mainResultHandler() {
        return new TypeHierarchyResultHandler();
    }

    @Bean
    public IterableResultHandler iterableResultHandler() {
        return new IterableResultHandler();
    }

    @Bean
    @ConditionalOnClass(TerminalSizeAware.class)
    public TerminalSizeAwareResultHandler terminalSizeAwareResultHandler() {
        return new TerminalSizeAwareResultHandler();
    }

    @Bean
    public AttributedCharSequenceResultHandler attributedCharSequenceResultHandler() {
        return new AttributedCharSequenceResultHandler();
    }

    @Bean
    public DefaultResultHandler defaultResultHandler() {
        return new DefaultResultHandler();
    }

    @Bean
    public ParameterValidationExceptionResultHandler parameterValidationExceptionResultHandler() {
        return new ParameterValidationExceptionResultHandler();
    }
}
