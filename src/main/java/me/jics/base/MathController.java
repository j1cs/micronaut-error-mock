package me.jics.base;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/math")
public class MathController {

    MathService mathService;

    MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @Post(uri = "/compute")
    Response computePost(@Body Request request) {
        log.info("computePost request={}", request);
        return mathService.compute(request);
    }
}