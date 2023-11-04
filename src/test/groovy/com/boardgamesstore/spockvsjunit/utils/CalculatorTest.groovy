package com.boardgamesstore.spockvsjunit.utils;

import spock.lang.Specification;

class CalculatorSpec extends Specification {
    def "test add"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "add is called with 2 and 3"
        int result = calculator.add(2, 3)

        then: "the result should be 5"
        result == 5
    }

    def "test subtract"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "subtract is called with 5 and 2"
        int result = calculator.subtract(5, 2)

        then: "the result should be 3"
        result == 3
    }

    def "test multiply"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "multiply is called with 3 and 4"
        int result = calculator.multiply(3, 4)

        then: "the result should be 12"
        result == 12
    }

    def "test divide"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "divide is called with 6 and 3"
        double result = calculator.divide(6, 3)

        then: "the result should be 2"
        result == 2.0d
    }

    def "test modulo"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "modulo is called with 10 and 3"
        int result = calculator.modulo(10, 3)

        then: "the result should be 1"
        result == 1
    }

    def "test power"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "power is called with 3 and 4"
        double result = calculator.power(3, 4)

        then: "the result should be 81"
        result == 81.0d
    }

    def "test negate"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "negate is called with -5"
        int result = calculator.negate(-5)

        then: "the result should be 5"
        result == 5
    }

    def "test abs"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "abs is called with -5"
        int result = calculator.abs(-5)

        then: "the result should be 5"
        result == 5
    }

    def "test sqrt"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "sqrt is called with 4"
        double result = calculator.sqrt(4)

        then: "the result should be 2"
        result == 2.0d
    }

    def "test floor"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "floor is called with 4.5"
        double result = calculator.floor(4.5)

        then: "the result should be 4"
        result == 4.0d
    }

    def "test ceil"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "ceil is called with 4.5"
        double result = calculator.ceil(4.5)

        then: "the result should be 5"
        result == 5.0d
    }

    def "test round"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "round is called with 4.5"
        double result = calculator.round(4.5)

        then: "the result should be 5"
        result == 5.0d
    }


    def "test random"() {
        given: "A calculator instance"
        Calculator calculator = new Calculator()

        when: "random is called with 0 and 1"
        double result = calculator.random(0, 1)

        then: "the result should be a double between 0 and 1"
        result >= 0.0d && result <= 1
    }
}
