package com.xxunghee;

/**
 * @author xxunghee
 */
public class Main {
    /**
     * <h1>Add Method</h1>
     * This is addition method.
     *
     * @param a  This is 1st parameter.
     * @param b  This is 2nd parameter.
     * @return   This returns the sum of a and b.
     */
    public static int add(int a, int b) {
        return a+b;
    }

    public static void main(String[] args) {
        int a = 97; // 'a'의 ASCII 코드 값
        int b = 98; // 'b'의 ASCII 코드 값

        /* a +b 값 출력하기 */
        System.out.println("a + b = " + add(a, b));
    }
}
