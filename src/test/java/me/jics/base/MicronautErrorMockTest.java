package me.jics.base;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import jakarta.inject.Inject;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
class MicronautErrorMockTest {

    @Inject
    MathService mathService;

    @Inject
    @Client("/")
    HttpClient client;


    @ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquare(Integer num, Integer square) {

        when( mathService.compute(num) )
                .then(invocation -> Long.valueOf(Math.round(Math.pow(num, 2))).intValue());

        final Integer result = client.toBlocking().retrieve(HttpRequest.GET("/math/compute/" + num), Integer.class);

        assertEquals(
                square,
                result
        );
        verify(mathService).compute(num);
    }

    @ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquarePost(Integer num, Integer square) {

        when( mathService.compute(num) )
                .then(invocation -> Long.valueOf(Math.round(Math.pow(num, 2))).intValue());

        Request request = Request.builder().num(num).build();
        final HttpResponse<Integer> response = client.toBlocking().exchange(HttpRequest.POST("/math/compute", request), Integer.class);
        assertNotNull(response.body());
        Integer result = response.body();
        assertEquals(
                square,
                result
        );
        verify(mathService).compute(num);
    }

    @MockBean(MathServiceImpl.class)
    MathService mathService() {
        return mock(MathService.class);
    }

}
