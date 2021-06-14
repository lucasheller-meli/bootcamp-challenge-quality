package com.challenge.quality.controllers.request;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

class HomeRequestTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void verifySizeHomeRequestAnnotation() {
        final HomeRequest request = HomeRequest.builder().name("ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABC")
                .neighborhood("ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABCABC")
                .rooms(Collections.singletonList(RoomRequest.builder().name("Quarto").width(5F).length(5F).build()))
                .build();
        Set<ConstraintViolation<HomeRequest>> violations = validator.validate(request);
        Assertions.assertEquals(2, violations.size());
    }

    @Test
    void verifyNotBlankHomeRequestAnnotation() {
        final HomeRequest request = HomeRequest.builder().build();
        Set<ConstraintViolation<HomeRequest>> violations = validator.validate(request);
        Assertions.assertEquals(3, violations.size());
    }

    @Test
    void verifyPatternHomeRequestAnnotation() {
        final HomeRequest request = HomeRequest.builder()
                .name("lucas")
                .neighborhood("luz")
                .rooms(Collections.singletonList(RoomRequest.builder().name("Quarto").width(5F).length(5F).build())).build();
        Set<ConstraintViolation<HomeRequest>> violations = validator.validate(request);
        //assert
        Assertions.assertEquals(1, violations.size());
    }

}