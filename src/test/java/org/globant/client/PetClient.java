package org.globant.client;

import io.restassured.response.Response;
import org.globant.models.Pet;

import static io.restassured.RestAssured.given;

public class PetClient {

    private static final String PET_PATH = "/pet";
    private static final String PET_FIND_BY_STATUS_PATH = "/pet/findByStatus";

    public Response createPet(Pet pet) {
        return given()
                .contentType("application/json")
                .body(pet)
                .when()
                .post(PET_PATH);
    }

    public Response getPetByStatus(String status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(PET_FIND_BY_STATUS_PATH);
    }

    public Response getPetById(Long petId) {
        return given()
                .pathParam("petId", petId)
                .when()
                .get(PET_PATH + "/{petId}");
    }
}
