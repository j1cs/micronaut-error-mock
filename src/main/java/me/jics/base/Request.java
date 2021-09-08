package me.jics.base;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
@Introspected
public class Request {
    private Integer num;
}
