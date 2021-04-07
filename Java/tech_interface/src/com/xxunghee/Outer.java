package com.xxunghee;

public class Outer implements OuterInterface{
    @Override
    public void outer() {
        System.out.println("call " + OuterInterface.loc);
    }
}
