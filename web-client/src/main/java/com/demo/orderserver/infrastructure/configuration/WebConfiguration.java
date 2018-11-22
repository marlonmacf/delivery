package com.demo.orderserver.infrastructure.configuration;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.MediaType;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfiguration {

    @Bean
    public RestTemplate restTemplate() {

        // List<MediaType> mediaTypes = new ArrayList<MediaType>();
        // mediaTypes.add(MediaType.TEXT_HTML);

        // Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
        // jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);

        // List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        // messageConverters.add(jaxbMessageConverter);

        // RestTemplate restTemplate = new RestTemplate();
        // restTemplate.setMessageConverters(messageConverters);

        // return restTemplate;
        return new RestTemplate();
    }
}