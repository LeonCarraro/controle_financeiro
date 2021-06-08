package com.leoncarraro.controlefinanceiro.api.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "controlefinanceiro")
@Getter
public class APIProperty {

    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {

        private boolean enableHttps = false;
        private String allowedOrigin = "http://localhost:4200";

    }

}
