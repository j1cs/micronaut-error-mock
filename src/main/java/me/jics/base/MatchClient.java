package me.jics.base;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://rickandmortyapi.com/api/character/233")
public interface MatchClient {
    @Get
    String getMorty();
}
