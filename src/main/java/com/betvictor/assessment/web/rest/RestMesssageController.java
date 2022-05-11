package com.betvictor.assessment.web.rest;

import com.betvictor.assessment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class RestMesssageController {
    private final UserService service;
  private final HealthEndpoint healthEndpoint;

  @Value("${spring.application.version}")
  private String version;

    @Autowired
    public RestMesssageController(UserService service, HealthEndpoint healthEndpoint) {

        this.service = service;
       this.healthEndpoint = healthEndpoint;

    }

    @PostMapping(path = "/v1/{toUser}")
    void sendMessage(@RequestBody String message, @PathVariable String toUser) {
        log.info("Rest send message {} to user {}", message, toUser);
        service.notifyUser(toUser, message);
    }
    @GetMapping(path = "/v1/health")
    public ResponseEntity<HealthComponent> health() {

        return ResponseEntity.ok(healthEndpoint.health());
    }
    @GetMapping(path = "/v1/version")
    public String version() {
        return version;
    }


}
