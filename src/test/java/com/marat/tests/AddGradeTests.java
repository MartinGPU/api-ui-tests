package com.marat.tests;

import com.codeborne.selenide.BearerTokenCredentials;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;

public class AddGradeTests extends TestBase {

    @Test
    public void getCookie() {

        step("Get cookie by api and set it to browser", () -> {
            String authorizationCookie =
                    given()
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParam("grant_type", grant)
                            .formParam("username", login)
                            .formParam("password", password)
                            .formParam("role", role)
                            .when()
                            .post(uri)
                            .then()
                            .statusCode(200)
                            .extract()
                            .cookie("refresh_token");

            step("Open minimal content and set cookie", () ->
                    open("/assets/images/loader-icon.webp"));

            step("Set cookie to to browser", () ->
                    getWebDriver().manage().addCookie(
                            new Cookie("refresh_token", authorizationCookie)));

            step("Open profile page", () ->
                    open("/profile"));

            step("checking children exist", () ->
                    $("[data-test-id='child-name']")).shouldHave(Condition.text("Кириченко Иван Семенович"));
        });
    }
}
