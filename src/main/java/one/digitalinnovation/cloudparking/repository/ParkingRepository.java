package one.digitalinnovation.cloudparking.repository;

import org.springframework.stereotype.Repository;
import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
