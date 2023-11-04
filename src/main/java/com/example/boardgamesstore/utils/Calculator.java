package com.example.boardgamesstore.utils;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int modulo(int a, int b) {
        return a % b;
    }

    public int power(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public int negate(int a) {
        return -a;
    }

    public int abs(int a) {
        return Math.abs(a);
    }

    public int sqrt(int a) {
        return (int) Math.sqrt(a);
    }

    public int floor(double a) {
        return (int) Math.floor(a);
    }

    public int ceil(double a) {
        return (int) Math.ceil(a);
    }

    public int round(double a) {
        return (int) Math.round(a);
    }

    public int round(float a) {
        return (int) Math.round(a);
    }

    public int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public double random(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }

    public float random(float min, float max) {
        return (float) (Math.random() * (max - min + 1) + min);
    }


}
