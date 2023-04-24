package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface CredentialsConfig extends Config {

    String username();
    String password();
    String grant_type();
    String role();
    String apiUrl();
    String webUrl();
}
