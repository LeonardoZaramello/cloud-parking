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
import one.digitalinnovation.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {

  private final ParkingRepository parkingRepository;

  public ParkingService(ParkingRepository parkingRepository) {
    this.parkingRepository = parkingRepository;
  }

  public List<Parking> findAll() {
    return parkingRepository.findAll();
  }

  public Parking findById(String id) {
    Parking parking =
        parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));

    return parking;
  }

  public Parking create(Parking parkingCreate) {
    String UUID = getUUID();
    parkingCreate.setId(UUID);
    parkingCreate.setEntryDate(LocalDateTime.now());
    parkingRepository.save(parkingCreate);

    return parkingCreate;
  }

  public Parking update(String id, Parking parkingUpdate) {
    Parking parking = findById(id);
    parking.setLicense(parkingUpdate.getLicense());
    parking.setState(parkingUpdate.getState());
    parking.setModel(parkingUpdate.getModel());
    parking.setColor(parkingUpdate.getColor());

    parkingRepository.save(parking);

    return parking;
  }

  public void delete(String id) {
    findById(id);

    parkingRepository.deleteById(id);
  }

  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
