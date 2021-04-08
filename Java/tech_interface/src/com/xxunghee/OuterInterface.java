package com.xxunghee;

public interface OuterInterface {
    String loc = "OUTER";

    void outer();

    interface InnerInterface extends BaseInterface{
        String loc = "INNER";

        void inner();
    }
}
