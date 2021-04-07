package com.xxunghee;

public interface OuterInterface {
    String loc = "OUTER";

    void outer();

    interface InnerInterface {
        String loc = "INNER";

        void inner();
    }
}
