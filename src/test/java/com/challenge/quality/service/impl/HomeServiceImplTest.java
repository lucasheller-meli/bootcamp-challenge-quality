package com.challenge.quality.service.impl;

import com.challenge.quality.controllers.request.HomeRequest;
import com.challenge.quality.controllers.request.RoomRequest;
import com.challenge.quality.controllers.response.HomeResponse;
import com.challenge.quality.exception.NeighborhoodException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class HomeServiceImplTest {

    @InjectMocks
    private HomeServiceImpl homeService;


    @Test
    @DisplayName("Analisa uma casa com sucesso.")
    void analyzeHomeSuccessfully() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);

        final HomeResponse response = homeService.analyzeHome(homeRequest);
        final HashMap expectedMeterPerRoom = new HashMap();
        expectedMeterPerRoom.put("Sala", 25.0F);
        expectedMeterPerRoom.put("Quarto", 9.0F);

        final HomeResponse expectedResponse = HomeResponse
                .builder()
                .totalMeters(34.0)
                .biggestRoom("Sala")
                .homePrice(3400.0)
                .metersPerRoom(expectedMeterPerRoom)
                .build();

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Analisa uma casa com um bairro que nao existe.")
    void analyzeHomeWithNonexistentNeighborhood() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);
        homeRequest.setNeighborhood("Morumbi");
        Assertions.assertThrows(NeighborhoodException.class, () -> homeService.analyzeHome(homeRequest));
    }

    private HomeRequest getHomeRequest(RoomRequest roomRequest, RoomRequest roomRequest2) {
        final HomeRequest homeRequest = HomeRequest
                .builder()
                .name("Casa")
                .neighborhood("Luz")
                .rooms(Arrays.asList(roomRequest, roomRequest2))
                .build();
        return homeRequest;
    }

    @Test
    @DisplayName("Analisa uma casa com maior quarto diferente do esperado..")
    void analyzeHomeWithDifferentBiggestRoom() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);

        final HomeResponse response = homeService.analyzeHome(homeRequest);

        Assertions.assertNotEquals("Quarto", response.getBiggestRoom());
        Assertions.assertEquals("Sala", response.getBiggestRoom());
    }

    @Test
    @DisplayName("Analisa uma casa verificando tamanho dos comodos.")
    void analyzeHomeVerifyingRoomsSize() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);

        final HomeResponse response = homeService.analyzeHome(homeRequest);

        Assertions.assertEquals(9.0F, response.getMetersPerRoom().get("Quarto"));
        Assertions.assertEquals(25.0F, response.getMetersPerRoom().get("Sala"));
        Assertions.assertEquals("Sala", response.getBiggestRoom());
    }

    @Test
    @DisplayName("Analisa uma casa com area total diferente do esperado.")
    void analyzeHomeWithDifferentTotalArea() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);

        final HomeResponse response = homeService.analyzeHome(homeRequest);

        Assertions.assertNotEquals(35.0, response.getTotalMeters());
        Assertions.assertEquals(34, response.getTotalMeters());
    }

    private RoomRequest getRoomRequest2() {
        return RoomRequest
                .builder()
                .length(5F)
                .width(5F)
                .name("Sala")
                .build();
    }

    private RoomRequest getRoomRequest() {
        return RoomRequest
                .builder()
                .length(3F)
                .width(3F)
                .name("Quarto")
                .build();
    }

}