package com.challenge.quality.controllers;

import com.challenge.quality.controllers.request.HomeRequest;
import com.challenge.quality.controllers.response.HomeResponse;
import com.challenge.quality.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeResponse> calculateHome(@Valid @RequestBody HomeRequest home) {
        return ResponseEntity.ok(homeService.analyzeHome(home));
    }
}
