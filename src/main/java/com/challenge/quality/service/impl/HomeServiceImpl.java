package com.challenge.quality.service.impl;

import com.challenge.quality.controllers.request.HomeRequest;
import com.challenge.quality.controllers.response.HomeResponse;
import com.challenge.quality.exception.NeighborhoodException;
import com.challenge.quality.service.HomeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    private static final HashMap<String, Float> neighborhoods;

    static {
        neighborhoods = new HashMap<>();
        neighborhoods.put("Faria Lima", 150F);
        neighborhoods.put("Moema", 130F);
        neighborhoods.put("Campo Belo", 110F);
        neighborhoods.put("Vila Mariana", 130F);
        neighborhoods.put("Luz", 100F);
    }

    public HomeResponse analyzeHome(HomeRequest homeRequest) {
        final var priceMeter = calculateTotalPrice(homeRequest.getNeighborhood());
        final HashMap<String, Float> sizePerRoom = new HashMap();
        homeRequest.getRooms().forEach(h -> sizePerRoom.put(h.getName(), h.getLength() * h.getWidth()));

        final List<Float> roomSizes = homeRequest.getRooms()
                .stream()
                .map(h -> (h.getLength() * h.getWidth())).collect(Collectors.toList());

        final double totalArea = roomSizes.stream().mapToDouble(Float::doubleValue).sum();

        return HomeResponse.builder()
                .totalMeters(totalArea)
                .biggestRoom(verifyBiggestRoom(sizePerRoom, Collections.max(roomSizes)))
                .metersPerRoom(sizePerRoom)
                .homePrice(totalArea * priceMeter)
                .build();

    }

    private Float calculateTotalPrice(String neighborhood) {
        Float pricePerMeter = 0F;
        for (Map.Entry<String, Float> entry : neighborhoods.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(neighborhood)) {
                pricePerMeter = entry.getValue();
            }
        }
        if (pricePerMeter <= 0) {
            throw new NeighborhoodException("");
        }

        return pricePerMeter;
    }

    private String verifyBiggestRoom(HashMap<String, Float> metrosPorComodo, Float areaMaiorComodo) {
        String biggestRoom = "";
        for (Map.Entry<String, Float> entry : metrosPorComodo.entrySet()) {
            if (entry.getValue().equals(areaMaiorComodo))
                biggestRoom = entry.getKey();
        }
        return biggestRoom;
    }
}
