package com.xxunghee;

public class Inner implements OuterInterface.InnerInterface{
    @Override
    public void inner() {
        System.out.println("call " + OuterInterface.InnerInterface.loc);
    }

    @Override
    public void baseFunc() {
        // 상속받은 인터페이스의 메소드도 오버라이딩 해야함
    }
}
