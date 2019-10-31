package com.guidesmiths.martianrobots.configuration;

import org.jline.reader.Parser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.ParameterResolver;
import org.springframework.shell.Shell;
import org.springframework.shell.standard.commands.*;

import java.util.List;

@Configuration
public class CustomStandardCommandsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Help.Command.class)
    @ConditionalOnProperty(prefix = "spring.shell.command.help", value = "enabled", havingValue = "true", matchIfMissing = true)
    public Help help(List<ParameterResolver> parameterResolvers) {
        return new Help(parameterResolvers);
    }

    @Bean
    @ConditionalOnMissingBean(Clear.Command.class)
    @ConditionalOnProperty(prefix = "spring.shell.command.clear", value = "enabled", havingValue = "true", matchIfMissing = true)
    public Clear clear() {
        return new Clear();
    }

    @Bean
    @ConditionalOnMissingBean(Quit.Command.class)
    @ConditionalOnProperty(prefix = "spring.shell.command.quit", value = "enabled", havingValue = "true", matchIfMissing = true)
    public Quit quit() {
        return new Quit();
    }

    /*@Bean
    @ConditionalOnMissingBean(CustomStacktrace.Command.class)
    @ConditionalOnProperty(prefix = "spring.shell.command.stacktrace", value = "enabled", havingValue = "true", matchIfMissing = true)
    public CustomStacktrace stacktrace() {
        return new CustomStacktrace();
    }*/

    @ConditionalOnMissingBean(Stacktrace.Command.class)
    @ConditionalOnProperty(prefix = "spring.shell.command.stacktrace", value = "enabled", havingValue = "true", matchIfMissing = true)
    public Stacktrace stacktrace() {
        return new Stacktrace();
    }

    @Bean
    @ConditionalOnMissingBean(Script.Command.class)
    @ConditionalOnProperty(prefix = "spring.shell.command.script", value = "enabled", havingValue = "true", matchIfMissing = true)
    public Script script(Shell shell, Parser parser) {
        return new Script(shell, parser);
    }
}
