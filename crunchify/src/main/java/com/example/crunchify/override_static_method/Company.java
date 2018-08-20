package com.example.crunchify.override_static_method;

import android.util.Log;

public class Company {

    // public static method which can not be overridden in Java
    public static void staticMethod() {
        Log.e("LOG_TAG", "Company: STATIC Instance method");
    }

    // non static method
    public void nonStaticMethod() {
        Log.e("LOG_TAG","Company: NON-STATIC method");
    }

}
