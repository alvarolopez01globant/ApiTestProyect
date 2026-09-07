package org.globant.client;

import io.restassured.response.Response;
import org.globant.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String USER_PATH ="/user";
    private static final String USER_LOGIN_PATH ="/user/login";
    private static final String USER_LOGOUT_PATH ="/user/logout";

    public Response createUser(User user){
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(USER_PATH);
    }

    public Response loginUser(String username, String password){
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get(USER_LOGIN_PATH);
    }

    public Response logoutUser(){
        return given()
                .when()
                .get(USER_LOGOUT_PATH);
    }


}
