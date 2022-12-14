package one.digitalinnovation.cloudparking.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.controller.mapper.ParkingMappper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

  private final ParkingService parkingService;
  private final ParkingMappper parkingMapper;

  public ParkingController(ParkingService parkingService, ParkingMappper parkingMapper) {
    super();
    this.parkingService = parkingService;
    this.parkingMapper = parkingMapper;
  }


  @GetMapping
  @ApiOperation("Find All Parkings")
  public ResponseEntity<List<ParkingDTO>> findAll() {
    List<Parking> parkingList = parkingService.findAll();
    List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  @ApiOperation("Find One Parking")
  public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
    Parking parking = parkingService.findById(id);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);

    return ResponseEntity.ok(result);
  }

  @PostMapping
  @ApiOperation("Create One Parking")
  public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
    Parking parkingCreate = parkingMapper.toParkingCreate(dto);
    Parking parking = parkingService.create(parkingCreate);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PutMapping("/{id}")
  @ApiOperation("Update One Parking")
  public ResponseEntity<ParkingDTO> update(@PathVariable String id,
      @RequestBody ParkingCreateDTO dto) {
    Parking parkingUpdate = parkingMapper.toParkingCreate(dto);
    Parking parkingUpdated = parkingService.update(id, parkingUpdate);
    ParkingDTO result = parkingMapper.toParkingDTO(parkingUpdated);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @PostMapping("/{id}")
  @ApiOperation("Exit One Parking")
  public ResponseEntity<ParkingDTO> exit(@PathVariable String id) {
    Parking parking = parkingService.checkOut(id);
    ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);

    return ResponseEntity.status(HttpStatus.OK).body(parkingDTO);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Delete One Parking")
  public ResponseEntity<ParkingDTO> delete(@PathVariable String id) {
    parkingService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
