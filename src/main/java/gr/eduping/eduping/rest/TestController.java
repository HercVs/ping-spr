package gr.eduping.eduping.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/open")
    public String openEndPoint() {
        return "This is the response for ALL";
    }

    @GetMapping("/authenticated")
    public String authenticatedEndPoint() {
        return "This is the response for AUTHENTICATED";
    }

    @GetMapping("/user")
    public String userEndPoint() {
        return "This is the response for USERS";
    }

    @GetMapping("/admin")
    public String adminEndPoint() {
        return "This is the response for ADMINS";
    }
}
