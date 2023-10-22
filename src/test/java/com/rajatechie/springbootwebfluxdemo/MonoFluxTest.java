package com.rajatechie.springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("rajatechie")
                .then(Mono.error(new RuntimeException("Exception occured")))
                .log();
        monoString.subscribe(System.out::print, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<?> fluxString = Flux.just("Spring", "Spring Boot", "Raja", "King")
                .concatWithValues("cloud")
                .concatWith(Flux.error(new RuntimeException("Flux error occured")))
                .log();
        fluxString.subscribe(System.out::print, (e) -> System.out.println(e.getMessage()));
    }
}
