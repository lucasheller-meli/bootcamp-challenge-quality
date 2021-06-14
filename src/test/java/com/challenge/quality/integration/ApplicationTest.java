package com.challenge.quality.integration;

import com.challenge.quality.controllers.request.HomeRequest;
import com.challenge.quality.controllers.request.RoomRequest;
import com.challenge.quality.controllers.response.HomeResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void shouldAnalyzeHomeSuccesfully() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);

        ResponseEntity<HomeResponse> homeResponse = testRestTemplate.postForEntity("/home", homeRequest, HomeResponse.class);

        Assertions.assertThat(homeResponse).isNotNull();
        Assertions.assertThat(homeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(homeResponse.getBody()).isNotNull();

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
        Assertions.assertThat(homeResponse.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    void shouldAnalyzeHomeWithConstraint() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);
        homeRequest.setName("teste");
        roomRequest.setName("nome");
        roomRequest.setLength(50F);
        roomRequest.setWidth(50F);

        ResponseEntity<HomeResponse> homeResponse = testRestTemplate.postForEntity("/home", homeRequest, HomeResponse.class);

        Assertions.assertThat(homeResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    void shouldAnalyzeHomeWithNonexistentNeighborhood() {
        final RoomRequest roomRequest = getRoomRequest();
        final RoomRequest roomRequest2 = getRoomRequest2();
        final HomeRequest homeRequest = getHomeRequest(roomRequest, roomRequest2);
        homeRequest.setNeighborhood("um lugar q n existe");

        ResponseEntity<HomeResponse> homeResponse = testRestTemplate.postForEntity("/home", homeRequest, HomeResponse.class);

        Assertions.assertThat(homeResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private HomeRequest getHomeRequest(RoomRequest roomRequest, RoomRequest roomRequest2) {
        return HomeRequest
                .builder()
                .name("Casa")
                .neighborhood("Luz")
                .rooms(Arrays.asList(roomRequest, roomRequest2))
                .build();
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
