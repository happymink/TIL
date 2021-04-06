package com.company;

public class Test implements Contract{
    public static void staticTest() {

    }

    public void nonStaticTest() {

    }

    @Override
    public static void staticOverrideTest() {
        // ERROR : static method는 오버라이딩 불가
    }

    @Override
    public void nonStaticOverrideTest() {
        //  OK   : non-static method는 오버라이딩 가능
    }
}
