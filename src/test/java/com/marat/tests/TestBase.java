package com.marat.tests;

import com.codeborne.selenide.Configuration;
import com.marat.config.CredentialsConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    public static String
            login,
            password,
            url,
            uri;

    @BeforeAll
    public static void beforeAll() {
        login = credentials.Email();
        password = credentials.Password();
        url = credentials.webUrl();
        uri = credentials.apiUrl();

        RestAssured.baseURI = credentials.apiUrl();
        Configuration.baseUrl = credentials.webUrl();
        Configuration.browserVersion = System.getProperty("version", "112.0");
        Configuration.browserSize = System.getProperty("size", "1366x900");
        Configuration.browser = System.getProperty("browser", "firefox");
    }
}
