package one.digitalinnovation.cloudparking.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import one.digitalinnovation.cloudparking.model.Parking;

@RestController
@RequestMapping("parking")
public class ParkingController {

  @GetMapping
  public List<Parking> findAll() {
    Parking instancePark = new Parking();


    return Arrays.asList(instancePark, instancePark);
  }
}
