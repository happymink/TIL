package com.company;

public interface Contract {
    static void staticOverrideTest() {
        // interface의 static method는 body를 가져야함
    }

    void nonStaticOverrideTest();
}
