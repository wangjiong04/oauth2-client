package com.example.oauth2.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
//                .clientId("a7553955a0c534ec5e6b")
//                .clientSecret("1795b30b425ebb79e424afa51913f1c724da0dbb")
//                .build();
//    }

        private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("github")
                .clientId("da77db2512b589933d87")
                .clientSecret("3e063c11e388feacc09c08e9a872d186239039ea")
                .scope(new String[]{"read:user"})
                .authorizationUri("https://github.com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("id")
                .clientName("GitHub")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

}
