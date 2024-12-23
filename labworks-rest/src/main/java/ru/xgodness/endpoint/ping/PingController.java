package ru.xgodness.endpoint.ping;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/ping")
public class PingController {
    private final PingClient pingClient;

    @Autowired
    public PingController(PingClient pingClient) {
        this.pingClient = pingClient;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Void> ping() {
        pingClient.ping();
        return ResponseEntity.status(204).build();
    }

}