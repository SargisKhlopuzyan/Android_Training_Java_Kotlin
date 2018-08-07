package com.example.sargiskh.android_training_java_kotlin.interfaces;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

public abstract interface InterfaceExample {

    // Constants
    public static final String constants = "CONSTANTS";

    // Default Methods
    void doSomething(); // a simple abstract method
    int doAnotherThing(String s); //accepts a String returns an Int
    boolean doEntirelyAnotherThing(Long l); // accepts a long value and returns a

    int returnType();


    void checkedExceptions() throws Exception;

    // Static Methods ** Static methods in interface should have a body **
    static void staticMethod() {

    }

    // Nested Types
    class NestedClass {
        void nestedFunction() {
        }
    }

}
