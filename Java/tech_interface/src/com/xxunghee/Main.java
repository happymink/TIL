package com.xxunghee;

public class Main {
    public static void main(String[] args) {
        OuterInterface outerInterface = new Outer();
        OuterInterface.InnerInterface innerInterface = new Inner();

        outerInterface.outer();
        innerInterface.inner();
    }
}
