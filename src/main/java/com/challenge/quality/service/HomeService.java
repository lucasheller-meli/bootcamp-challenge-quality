package com.challenge.quality.service;

import com.challenge.quality.controllers.request.HomeRequest;
import com.challenge.quality.controllers.response.HomeResponse;

public interface HomeService {

    HomeResponse analyzeHome(HomeRequest homeRequest);
}
