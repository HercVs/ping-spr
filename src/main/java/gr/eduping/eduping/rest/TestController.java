package gr.eduping.eduping.rest;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Hidden
    @GetMapping("/open")
    public String openEndPoint() {
        return "This is the response for ALL";
    }

    @Hidden
    @GetMapping("/authenticated")
    public String authenticatedEndPoint() {
        return "This is the response for AUTHENTICATED";
    }

    @Hidden
    @GetMapping("/user")
    public String userEndPoint() {
        return "This is the response for USERS";
    }

    @Hidden
    @GetMapping("/admin")
    public String adminEndPoint() {
        return "This is the response for ADMINS";
    }
}
