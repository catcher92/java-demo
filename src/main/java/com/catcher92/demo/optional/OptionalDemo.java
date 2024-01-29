package com.catcher92.demo.optional;

import java.util.Optional;

/**
 * Optional无法被序列化
 */

class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}

class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;

    public String getName() {
        return name;
    }
}

public class OptionalDemo {

    Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }

    /**
     * 两个Optional组合
     */
    Optional<Insurance> findCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    /**
     * 多层Optional通过flatmap来展开
     */
    String getCarInsuranceName(Person person) {
        return Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
