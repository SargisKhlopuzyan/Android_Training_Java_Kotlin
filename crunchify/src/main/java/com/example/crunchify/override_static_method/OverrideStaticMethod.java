package com.example.crunchify.override_static_method;

public class OverrideStaticMethod {

    @Deprecated
    public static void test() {
        Company cmp = new SubCompany();
        // if we can override static, this should call method from Child class
        cmp.staticMethod();
        // Eclipse should show warning: The static method staticMethod() from the type Company should be accessed in a static way
        cmp.nonStaticMethod();
    }
}
