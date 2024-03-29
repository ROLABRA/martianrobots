package com.guidesmiths.martianrobots.configuration.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.shell.ResultHandler;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;

@Configuration
@Import(CustomResultHandlerConfig.class)
public class CustomShellAutoConfiguration {

    @Bean
    @Qualifier("spring-shell")
    public ConversionService shellConversionService(ApplicationContext applicationContext) {
        Collection<Converter> converters = applicationContext.getBeansOfType(Converter.class).values();
        Collection<GenericConverter> genericConverters = applicationContext.getBeansOfType(GenericConverter.class).values();
        Collection<ConverterFactory> converterFactories = applicationContext.getBeansOfType(ConverterFactory.class).values();

        DefaultConversionService defaultConversionService = new DefaultConversionService();
        for (Converter converter : converters) {
            defaultConversionService.addConverter(converter);
        }
        for (GenericConverter genericConverter : genericConverters) {
            defaultConversionService.addConverter(genericConverter);
        }
        for (ConverterFactory converterFactory : converterFactories) {
            defaultConversionService.addConverterFactory(converterFactory);
        }
        return defaultConversionService;
    }

    @Bean
    @ConditionalOnMissingBean(Validator.class)
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public CustomShell shell(@Qualifier("main") ResultHandler resultHandler) {
        return new CustomShell(resultHandler);
    }

}
