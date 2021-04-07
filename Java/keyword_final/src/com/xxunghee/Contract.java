package com.xxunghee;

public interface Contract {
    final void finalFunc(); // ERROR : Interface에서 final method 정의 불가(오버라이딩이 안되므로)

    void func();            // OK
}
