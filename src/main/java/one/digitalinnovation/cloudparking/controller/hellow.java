package one.digitalinnovation.cloudparking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class hellow {

  @GetMapping
  public String getAllEntregas() {
    return "Ola";
  }
}
