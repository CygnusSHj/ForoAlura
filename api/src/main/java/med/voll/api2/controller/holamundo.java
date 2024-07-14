package med.voll.api2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class holamundo {
    @GetMapping
    public String holaMundo(){
        return "Hola Mundo xd asdasd";
    }
}
