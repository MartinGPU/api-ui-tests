package com.marat.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class ApiUiTests extends TestBase {

    @Test
    public void getCookie() {
        step("Get cookie by api and set it to browser", () -> {
            String authorizationCookie =
                    given()
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParam("Email", login)
                            .formParam("Password", password)
                            .when()
                            .post(uri)
                            .then()
                            .statusCode(302)
                            .extract()
                            .cookie("NOPCOMMERCE.AUTH");

            step("Open minimal content and set cookie", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookie to browser", () ->
                    getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authorizationCookie)));
        });
        step("Open profile page", () -> {
            open(url);
        });
        step("Products count in cart", () -> {
            $(".cart-qty").shouldHave(Condition.text("(5)"));
        });
    }
}



