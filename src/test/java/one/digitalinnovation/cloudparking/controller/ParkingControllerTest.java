package one.digitalinnovation.cloudparking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;

import java.util.logging.Level;
import java.util.logging.LogManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase {

  static {
    // Postgres JDBC driver uses JUL; disable it to avoid annoying, irrelevant, stderr logs during
    // connection testing
    LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
  }
  @LocalServerPort
  private int randomPort;

  @BeforeEach
  public void setUpTest() {
    System.out.println(randomPort);
    RestAssured.port = randomPort;
  }

  @Test
  void findAll() {
    RestAssured.given().auth().basic("user", "dio@123456").when().get("/parking").then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  void createParking() {
    ParkingCreateDTO parkingDTO = new ParkingCreateDTO();
    parkingDTO.setLicense("AXJ-4925");
    parkingDTO.setState("BH");
    parkingDTO.setModel("Tesla");
    parkingDTO.setColor("Azul");

    RestAssured.given().auth().basic("user", "dio@123456").when().contentType(ContentType.JSON)
        .body(parkingDTO).post("/parking").then().statusCode(HttpStatus.CREATED.value())
        .body("license", Matchers.equalTo("AXJ-4925")).body("state", Matchers.equalTo("BH"))
        .body("model", Matchers.equalTo("Tesla")).body("color", Matchers.equalTo("Azul"));
  }


}
