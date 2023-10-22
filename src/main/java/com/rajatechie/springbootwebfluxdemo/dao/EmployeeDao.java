package com.rajatechie.springbootwebfluxdemo.dao;

import com.rajatechie.springbootwebfluxdemo.dto.Employee;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class EmployeeDao {

    public static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Employee> getEmployee() {
        return IntStream.rangeClosed(0, 10)
                .peek(EmployeeDao::sleepExecution)
                .peek(i -> System.out.println("Processing Count "+ i))
                .mapToObj(i -> new Employee(i, "Employee "+i))
                .collect(Collectors.toList());
    }

    public Flux<Employee> getEmployeeStream() {
        return Flux.range(0, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> new Employee(i, "Employee "+i))
                .map(i-> new Employee(i, "Employee "+i));
    }
}
