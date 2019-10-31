package com.guidesmiths.martianrobots.configuration;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.shell.ParameterDescription;
import org.springframework.shell.ParameterResolver;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.Utils;
import org.springframework.shell.result.TerminalAwareResultHandler;

import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TestParameterValidationExceptionResultHandler
        extends TerminalAwareResultHandler<ParameterValidationException> {

    @Autowired
    private List<ParameterResolver> parameterResolvers;

    @Override
    protected void doHandleResult(ParameterValidationException result) {
        terminal.writer().println(new AttributedString("The following constraints were not met:",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)).toAnsi());
        result.getConstraintViolations().stream()
                .forEach(v -> {
                    Optional<Integer> parameterIndex = StreamSupport.stream(v.getPropertyPath().spliterator(), false)
                            .filter(n -> n.getKind() == ElementKind.PARAMETER)
                            .map(n -> ((Path.ParameterNode) n).getParameterIndex())
                            .findFirst();

                    MethodParameter methodParameter = Utils.createMethodParameter(result.getMethodTarget().getMethod(),
                            parameterIndex.get());
                    List<ParameterDescription> descriptions = findParameterResolver(methodParameter)
                            .describe(methodParameter).collect(Collectors.toList());
                    if (descriptions.size() == 1) {
                        ParameterDescription description = descriptions.get(0);
                        AttributedStringBuilder ansi = new AttributedStringBuilder(100);
                        ansi.append("\t").append(description.keys().get(0), AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN).bold());
                        ansi.append(" ").append(description.formal(), AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN).underline());
                        String msg = String.format(" : %s (You passed '%s')",
                                v.getMessage(),
                                String.valueOf(v.getInvalidValue())
                        );
                        ansi.append(msg, AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

                        terminal.writer().println(ansi.toAnsi(terminal));
                    }
                    // Several formals for one method param, must be framework like JCommander, etc
                    else {
                        // Output toString() for now...
                        terminal.writer().println(new AttributedString(v.toString(),
                                AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)).toAnsi(terminal));
                    }

                });
    }

    private ParameterResolver findParameterResolver(MethodParameter methodParameter) {
        return parameterResolvers.stream().filter(pr -> pr.supports(methodParameter)).findFirst().get();
    }
}

