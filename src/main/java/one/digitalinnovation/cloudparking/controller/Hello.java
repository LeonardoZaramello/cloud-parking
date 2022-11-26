package one.digitalinnovation.cloudparking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class Hello {

  @GetMapping
  public String HelloWorld() {
    return "Bem vindo ao Cloud Parking";
  }
}
