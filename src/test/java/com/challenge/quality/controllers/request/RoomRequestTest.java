package com.challenge.quality.controllers.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class RoomRequestTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void verifyRoomRequestSizeAnnotation() {
        final RoomRequest request = RoomRequest.builder().name("ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABC")
                .length(34F)
                .width(26F)
                .build();
        Set<ConstraintViolation<RoomRequest>> violations = validator.validate(request);
        Assertions.assertEquals(3, violations.size());
    }

    @Test
    void verifyRoomRequestWithEmptyAttributes() {
        final RoomRequest request = RoomRequest.builder()
                .build();
        Set<ConstraintViolation<RoomRequest>> violations = validator.validate(request);
        Assertions.assertEquals(3, violations.size());
    }

    @Test
    void verifyRoomRequestMinValue() {
        final RoomRequest request = RoomRequest.builder()
                .name("abc")
                .length(0F)
                .width(0F)
                .build();
        Set<ConstraintViolation<RoomRequest>> violations = validator.validate(request);
        Assertions.assertEquals(3, violations.size());
    }

}