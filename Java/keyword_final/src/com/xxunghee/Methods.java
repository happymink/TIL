package com.xxunghee;

public class Methods implements Contract {
    public final void finalMethod() {}

    public void generalMethod() {}

    @Override
    public final void func() {
        // OK : Interface의 method를 final로 오버라이딩 가능 -> 다른 곳에서 오버라이딩 불가
    }
}
