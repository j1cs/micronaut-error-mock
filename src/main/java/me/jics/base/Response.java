package me.jics.base;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@Introspected
public class Response {
    private Integer msg;
}
