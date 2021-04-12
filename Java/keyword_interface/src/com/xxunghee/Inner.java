package com.xxunghee;

public class Inner extends Base implements OuterInterface.InnerInterface{
    @Override
    public void inner() {
        System.out.println("call " + OuterInterface.InnerInterface.loc);
    }

    /**
     * 부모 클래스에서 구현이 되어 있으므로 {@link Base#baseFunc()} 오버라이딩하지 않아도 됨
     * 이 코드처럼 구현 내용이 다른 경우 오버라이딩
     */
    @Override
    public void baseFunc() {
        // 상속받은 인터페이스의 메소드도 오버라이딩 해야함
        System.out.println("Call Inner class's baseFunc()");
    }
}
