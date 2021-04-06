package com.company;

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

    }
}
