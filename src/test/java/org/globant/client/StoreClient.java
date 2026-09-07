package org.globant.client;

import io.restassured.response.Response;

public class StoreClient {

    private static final String STORE_PATH = "/store/order";

    public Response createOrdrer(Object order) {
        return io.restassured.RestAssured.given()
                .contentType("application/json")
                .body(order)
                .when()
                .post(STORE_PATH);
    }
}
