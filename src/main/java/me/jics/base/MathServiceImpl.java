package me.jics.base;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class MathServiceImpl implements MathService {

    private final MatchClient client;

    public MathServiceImpl(MatchClient client) {
        this.client = client;
    }

    @Override
    public Response compute(Request request) {
        return Response.builder().msg(request.getNum() * 4).build();
    }
}
