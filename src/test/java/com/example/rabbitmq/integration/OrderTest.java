package com.example.rabbitmq.integration;

import com.example.rabbitmq.dto.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class OrderTest {

    private String BASE_URI = "http://localhost:9292";

    @Test
    public void t() {

        Order order = Order.builder().name("Rafael Nascimento Senior Software Engineer").qty(5).price(999).build();

        given().baseUri(BASE_URI)
            .basePath("/order/{restaurantName}")
            .pathParam("restaurantName", "rafa")
            .request()
            .contentType(JSON)
            .body(order)
            .log().all()
            .when()
            .post()
            .then().log().all()
            .assertThat().statusCode(SC_OK);
    }
}
