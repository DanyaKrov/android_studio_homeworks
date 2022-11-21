package com.example.homework_9;

public class Problem {
    private int result;

    public int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public int getResult() {
        return result;
    }

    public int getNoiseResult() {
        return result + getRandom(-4, 4);
    }

    public String getProblem() {
        int a = getRandom(-50, 50);
        int b = getRandom(0, 50);
        char sign = getRandomSign();
        if (sign == '+')
            result = a + b;
        else if (sign == '*')
            result = a * b;
        else if (sign == '/')
            result = a / b;
        else
            result = a - b;
        return a + " " + sign + " " + b;
    }

    private char getRandomSign() {
        return new char[]{'*', '/', '+', '-'}[getRandom(0, 4)];
    }

}
