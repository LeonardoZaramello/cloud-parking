package one.digitalinnovation.cloudparking.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import one.digitalinnovation.cloudparking.model.Parking;

@Service
public class ParkingService {

  private static Map<String, Parking> parkingMap = new HashMap<>();

  static {
    var id = getUUID();
    Parking parking = new Parking(id, "DMS-1111", "SC", "Celta", "Preto");
    parkingMap.put(id, parking);
  }

  public List<Parking> findAll() {
    Parking instancePark = new Parking();


    return parkingMap.values().stream().collect(Collectors.toList());
  }

  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
