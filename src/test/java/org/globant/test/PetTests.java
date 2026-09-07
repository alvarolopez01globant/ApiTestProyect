package org.globant.test;

import io.restassured.response.Response;
import org.globant.client.PetClient;
import org.globant.models.Pet;
import org.globant.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class PetTests extends BaseTest {

    private PetClient petClient;

    @BeforeMethod
    public void prepareClient() {
        petClient = new PetClient();
    }

    @Test(description = "3. Listar todas las mascotas que tengan el status disponible")
    public void testListAvailablePets() {
        Response response = petClient.getPetByStatus("available");

        response.then()
                .statusCode(200)
                .body("status", everyItem(equalTo("available")));

        List<Pet> pets = response.jsonPath().getList("", Pet.class);
        Assert.assertFalse(pets.isEmpty(), "La lista de mascotas disponibles no debería estar vacía");
    }

    @Test(description = "4. Consultar los datos de una mascota en específico")
    public void testGetSpecificPetById() {
        // Se crea una mascota dinámicamente para asegurar existencia y atomicidad
        long petId = System.currentTimeMillis();
        Pet pet = Pet.builder()
                .id(petId)
                .name("Firulais_" + petId)
                .photoUrls(Collections.singletonList("https://perfdog.com/dog.png"))
                .status("available")
                .build();

        petClient.createPet(pet);

        Response response = petClient.getPetById(petId);

        response.then().statusCode(200);
        Pet actualPet = response.as(Pet.class);
        Assert.assertEquals(actualPet.getId(), Long.valueOf(petId));
        Assert.assertEquals(actualPet.getName(), pet.getName());
    }
}