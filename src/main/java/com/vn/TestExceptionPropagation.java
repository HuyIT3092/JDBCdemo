package com.vn;

public class TestExceptionPropagation {
    public void m() throws java.io.IOException {
        throw new java.io.IOException("device error"); // checked exception
    }

    public void n() throws java.io.IOException{
        m();
    }

    public void p() {
        try {
            n();
        } catch (Exception e) {
            System.out.println("exception handeled");
        }
    }

}
