package com.example.sargiskh.android_training_java_kotlin.interfaces;

public class ClassExample implements InterfaceExample {

    @Override
    public void doSomething() {

    }

    @Override
    public int doAnotherThing(String s) {
        return 0;
    }

    @Override
    public boolean doEntirelyAnotherThing(Long l) /*throws Exception*/ /*ERROR*/  {
        return false;
    }

    @Override
    public void checkedExceptions() /*throws Exception*/ /*CORRECTED but can be emitted*/ {

    }

    @Override
    public int returnType() {
        return 10;
    }


}
