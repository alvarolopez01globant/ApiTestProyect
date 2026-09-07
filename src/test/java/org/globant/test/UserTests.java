package org.globant.test;

import io.restassured.response.Response;
import org.globant.client.UserClient;
import org.globant.models.User;
import org.globant.utils.BaseTest;
import org.globant.utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserTests extends BaseTest {

    private UserClient userClient;

    @BeforeMethod
    public void prepareClient() {
        userClient = new UserClient();
    }

    @Test(description = "1. Crear un usuario de forma exitosa")
    public void testCreateUser() {
        User user = DataGenerator.generateRandomUser();

        Response response = userClient.createUser(user);

        response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(String.valueOf(user.getId())));
    }

    @Test(description = "2. Hacer login con un usuario recién creado (Independiente)")
    public void testLoginWithNewlyCreatedUser() {
        // Garantía de atomicidad: se crea el usuario específico para esta prueba
        User user = DataGenerator.generateRandomUser();
        userClient.createUser(user);

        Response loginResponse = userClient.loginUser(user.getUsername(), user.getPassword());

        loginResponse.then().statusCode(200);
        String message = loginResponse.jsonPath().getString("message");
        Assert.assertTrue(message.contains("logged in user session"), "El mensaje de respuesta no confirma el login");
    }

    @Test(description = "6. Hacer el logout en la aplicación")
    public void testLogout() {
        Response response = userClient.logoutUser();

        response.then()
                .statusCode(200)
                .body("message", equalTo("ok"));
    }
}