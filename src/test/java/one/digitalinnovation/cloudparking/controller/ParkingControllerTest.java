package one.digitalinnovation.cloudparking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {

  @LocalServerPort
  private int randomPort;

  @BeforeEach
  public void setUpTest() {
    System.out.println(randomPort);
    RestAssured.port = randomPort;
  }

  @Test
  void findAll() {
    RestAssured.given().when().get("/parking").then().statusCode(200).body("license[1]",
        Matchers.equalTo("DMS-1111"));
  }
}
