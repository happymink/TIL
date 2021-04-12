package com.xxunghee;

public class Test implements Contract {
    static int sa = 0;
    int a = 0;

    public static void staticTest() {
        sa = 1; // OK
        a = 1;  // ERROR : static method 생성 시 a가 할당되지 않았을 수 있음 => static method는 non-static variable 접근 불가
    }

    public void nonStaticTest() {
        sa = 2; // OK
        a = 2;  // OK
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
