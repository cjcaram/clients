package com.cjcaram.clients.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {
    // TODO: we can move to submodule the configuration that is common between microservices
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
