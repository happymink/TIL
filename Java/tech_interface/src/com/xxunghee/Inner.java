package com.xxunghee;

public class Inner implements OuterInterface.InnerInterface{
    @Override
    public void inner() {
        System.out.println("call " + OuterInterface.InnerInterface.loc);
    }
}
