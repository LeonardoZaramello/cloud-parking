package one.digitalinnovation.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;

@Service
public class ParkingService {

  private static Map<String, Parking> parkingMap = new HashMap<>();

  static {
    var id1 = getUUID();
    var id2 = getUUID();
    Parking parking1 = new Parking(id1, "DMS-1111", "SC", "Celta", "Preto");
    Parking parking2 = new Parking(id2, "ASD-4231", "RJ", "Uno", "Azul");
    parkingMap.put(id1, parking1);
    parkingMap.put(id2, parking2);
  }

  public List<Parking> findAll() {
    return parkingMap.values().stream().collect(Collectors.toList());
  }

  public Parking findById(String id) {
    Parking parking = parkingMap.get(id);

    if (parking == null) {
      throw new ParkingNotFoundException(id);
    }

    return parking;
  }

  public Parking create(Parking parkingCreate) {
    String UUID = getUUID();
    parkingCreate.setId(UUID);
    parkingCreate.setEntryDate(LocalDateTime.now());
    parkingMap.put(UUID, parkingCreate);

    return parkingCreate;
  }

  public Parking update(String id, Parking parkingUpdate) {
    Parking parking = findById(id);
    parking.setLicense(parkingUpdate.getLicense());
    parking.setState(parkingUpdate.getState());
    parking.setModel(parkingUpdate.getModel());
    parking.setColor(parkingUpdate.getColor());

    parkingMap.replace(id, parking);

    return parking;
  }

  public void delete(String id) {
    findById(id);

    parkingMap.remove(id);
  }

  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
