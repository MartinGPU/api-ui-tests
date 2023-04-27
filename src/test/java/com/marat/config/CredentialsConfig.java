package com.marat.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface CredentialsConfig extends Config {

    String Email();

    String Password();

    String apiUrl();

    String webUrl();
}
