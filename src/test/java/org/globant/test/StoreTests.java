package org.globant.test;

import io.restassured.response.Response;
import org.globant.client.StoreClient;
import org.globant.models.Order;
import org.globant.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StoreTests extends BaseTest {

    private StoreClient storeClient;

    @BeforeMethod
    public void prepareClient() {
        storeClient = new StoreClient();
    }

    @Test(description = "5. Crear una orden (compra) para una mascota")
    public void testCreateOrder() {
        long orderId = System.currentTimeMillis();
        Order order = Order.builder()
                .id(orderId)
                .petId(10L)
                .quantity(2)
                .shipDate("2026-09-07T12:00:00.000Z")
                .status("placed")
                .complete(true)
                .build();

        Response response = storeClient.createOrdrer(order);

        response.then().statusCode(200);
        Order createdOrder = response.as(Order.class);
        Assert.assertEquals(createdOrder.getId(), Long.valueOf(orderId));
        Assert.assertEquals(createdOrder.getStatus(), "placed");
    }
}
