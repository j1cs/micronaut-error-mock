package me.jics.base;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

@Controller("/math")
public class MathController {

    MathService mathService;

    MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @Get(uri = "/compute/{number}", processes = MediaType.TEXT_PLAIN)
    String compute(Integer number) {
        return String.valueOf(mathService.compute(number));
    }

    @Get(uri = "/compute", processes = MediaType.TEXT_PLAIN)
    String computePost(@Body Request request) {
        return String.valueOf(mathService.compute(request.getNum()));
    }
}