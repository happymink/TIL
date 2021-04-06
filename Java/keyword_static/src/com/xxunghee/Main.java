package com.xxunghee;

public class Main {
    static class Static {
        // static inner class
    }

    class NonStatic {
        // non-static inner class
    }

    class A extends Static {
        // (non-static) A -> (non-static) NonStatic     ==> OK
    }

    class B extends NonStatic {
        // (non-static) B -> (non-static) NonStatic     ==> OK
    }

    class C extends Main {
        // (non-static) C -> (non-static) Main          ==> OK
    }

    static class SA extends Static {
        // (static) SA -> (static) Static               ==> OK
    }

    static class SB extends NonStatic {
        // (static) SB -> (non-static) NonStatic        ==> ERROR
        // static class는 non-static class를 상속 받을 수 없음
    }

    static class SC extends Main {
        // (static) SC -> (static) Main                 ==> OK
    }

    public static void main(String[] args) {
        Test test = new Test();

        Test.staticTest();      // OK
        test.staticTest();      // WARNING : static method는 인스턴스 없이 접근 가능

        Test.nonStaticTest();   // ERROR   : non-static method는 인스턴스 생성 후 접근 가능
        test.nonStaticTest();   // OK

        // sa와 a를 100으로 설정(초기값: sa = 0, a = 0)
        test.sa = 100;
        test.a = 100;

        Test t = new Test();

        System.out.println(t.sa); // value: 100
        System.out.println(t.a);  // value:  0
    }
}
