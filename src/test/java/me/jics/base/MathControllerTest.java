package me.jics.base;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.http.client.annotation.*;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
class MathControllerTest {

    @Inject
    MathService mathService;

    @Inject
    @Client("/")
    HttpClient client;


    @Test
    void testComputeNumToSquarePost() {
        Request request = Request.builder().num(2).build();
        when( mathService.compute(request) )
                .then(invocation -> Response.builder().msg(2*2).build());


        final HttpResponse<Response> response = client.toBlocking().exchange(HttpRequest.POST("/math/compute", request), Response.class);
        assertNotNull(response.body());
        Response result = response.body();
        assertEquals(
                8,
                result.getMsg()
        );
        verify(mathService).compute(request);
    }

    @MockBean(MathServiceImpl.class)
    MathService service() {
        return mock(MathService.class, withSettings().verboseLogging());
    }

}
