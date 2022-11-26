package one.digitalinnovation.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {

  private final ParkingRepository parkingRepository;

  public ParkingService(ParkingRepository parkingRepository) {
    this.parkingRepository = parkingRepository;
  }


  @Transactional(readOnly = true)
  public List<Parking> findAll() {
    return parkingRepository.findAll();
  }

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public Parking findById(String id) {
    Parking parking =
        parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));

    return parking;
  }

  @Transactional
  public Parking create(Parking parkingCreate) {
    String UUID = getUUID();
    parkingCreate.setId(UUID);
    parkingCreate.setEntryDate(LocalDateTime.now());
    parkingRepository.save(parkingCreate);

    return parkingCreate;
  }

  @Transactional
  public Parking update(String id, Parking parkingUpdate) {
    Parking parking = findById(id);
    parking.setLicense(parkingUpdate.getLicense());
    parking.setState(parkingUpdate.getState());
    parking.setModel(parkingUpdate.getModel());
    parking.setColor(parkingUpdate.getColor());

    parkingRepository.save(parking);

    return parking;
  }

  @Transactional
  public Parking checkOut(String id) {
    Parking parking = findById(id);
    parking.setExitDate(LocalDateTime.now());

    parking.setBill(ParkingCheckOut.getBill(parking));
    parkingRepository.save(parking);

    return parking;
  }

  @Transactional
  public void delete(String id) {
    findById(id);

    parkingRepository.deleteById(id);
  }

  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
