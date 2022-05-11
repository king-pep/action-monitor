package com.betvictor.assessment.web.rest;

import com.betvictor.assessment.health.ApplicationHealth;
import com.betvictor.assessment.service.UserService;
import com.betvictor.assessment.version.AppVersionConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class RestMesssageController {
    private final UserService service;
    private final RestTemplate restTemplate;
    private  AppVersionConfig appVersionConfig;
    @Value("${info.app.version:unknown}")
    private String version;
@Value("${info.app.health.url:unknown}")
    private String healthUrl;

    @Autowired
    public RestMesssageController(UserService service, RestTemplate restTemplate, AppVersionConfig appVersionConfig) {
        this.service = service;
        this.restTemplate = restTemplate;
        this.appVersionConfig = appVersionConfig;
    }

    @PostMapping(path = "/v1/{toUser}")
    void sendMessage(@RequestBody String message, @PathVariable String toUser) {
        log.info("Rest send message {} to user {}", message, toUser);
        service.notifyUser(toUser, message);
    }
    //application health status
    @GetMapping (path = "/v1/health/status")
    public ApplicationHealth healthStatus() {
        return restTemplate.getForObject(healthUrl, ApplicationHealth.class);
    }
    //Get application version
    @GetMapping (path = "/v1/version")
    public String version() {
        return version + " : " + AppVersionConfig.getVersion();
    }
}
