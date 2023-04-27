package com.marat.tests;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AddGradeTests extends TestBase {

    @Test
    public void getCookie() {

        step("Get cookie by api and set it to browser", () -> {
            String authorizationToken =
                    given()
                            .contentType("application/x-www-form-urlencoded")
                            .formParam("grant_type", grant)
                            .formParam("username", login)
                            .formParam("password", password)
                            .formParam("role", role)
                            .when()
                            .post(uri)
                            .then()
                            .log().all()
                            .statusCode(200)
                            .extract()
                            .response().jsonPath().getString("access_token");
            System.out.println(authorizationToken);

            RestAssured
                    .given()
                    .log().all()
                    .when()
                    .contentType("application/json")
                    .header("Authorization", "Bearer" + " " + authorizationToken)
                    .get("https://educont.ru/api/v1/profile/detail")
                    .then()
                    .statusCode(200)
                    .body("login", Matchers.equalTo("hkogifjotlbo@knowledgemd.com"))
                    .extract().response().jsonPath().prettyPrint();


//            step("Open minimal content and set cookie", () ->
//                    open("/assets/sprite.svg"));
//
//            step("Set cookie to browser", () ->
//                    getWebDriver()
//
//            step("Open profile page", () ->
//                    open(url));
//
//            step("checking children exist", () ->
//                    $$(".user__contact__text").get(1).shouldHave(Condition.text("hkogifjotlbo@knowledgemd.com")));
        });
        }
    }




