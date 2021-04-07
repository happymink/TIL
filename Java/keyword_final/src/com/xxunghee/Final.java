package com.xxunghee;

public final class Final {
    private final String str; // 생성자에서 초기화하거나, 변수 생성 시 초기화 해야함
    private String abc;

    public Final(String str, String abc) {
        this.str = str; // OK
        this.abc = abc; // OK
    }

    public void setStr(String str) {
        this.str = str;  // ERROR : 생성자로 초기화 된 이후 값 변경 불가
    }

    public void setAbc(String abc) {
        this.abc = abc;  // OK
    }

    public String getStr() {
        return this.str; // OK
    }

    public String getAbc() {
        return this.abc; // OK
    }
}

class InheritanceTest extends Final {
    // ERROR : final class 상속 불가
}