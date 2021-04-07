package com.xxunghee;

public class Main {
    public static void main(String[] args) {
        final String s1 = "hello";
        String s2 = "hello";

        s1 = "Jello";   // ERROR : final 키워드가 붙은 변수는 값의 변경 불가능
        s2 = "Yellow";  // OK

        final int fa;
        int a;

        fa = 0; // 값 할당
        a = 0;  // 초기값 할당

        fa = 3; // ERROR : final 키워드가 붙은 변수는 값의 변경 불가능
        a = 3;  // OK
    }
}