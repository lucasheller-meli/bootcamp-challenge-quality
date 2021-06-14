package com.challenge.quality.controllers.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class HomeResponse {

    private Double totalMeters;
    private Double homePrice;
    private String biggestRoom;
    private Map<String, Float> metersPerRoom;
}
