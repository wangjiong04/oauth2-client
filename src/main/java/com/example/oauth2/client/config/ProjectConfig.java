package com.example.oauth2.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ClientRegistrationRepository clientRepository() {
        ClientRegistration c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }

//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .clientId("da77db2512b589933d87")
//                .clientSecret("3e063c11e388feacc09c08e9a872d186239039ea")
//                .build();
//    }

        private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("client")
                .clientId("client")
                .clientSecret("secret")
                .scope(new String[]{"read"})
                .authorizationUri("http://auth-server:8080/oauth/authorize")
                .tokenUri("http://auth-server:8080/oauth/token")
                .userNameAttributeName("id")
                .userInfoUri("http://auth-server:8080/user")
                .clientName("client")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("https://www.baidu.com")
                //.redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login().userInfoEndpoint();

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

}
