package com.example.crunchify.annotation;

import android.util.Log;

public class DocumentedAnnotations {

    public static void test() {
        new DocumentedAnnotations().performRetention();
        new DocumentedAnnotations().performDocumented();
    }

    @Annotation_Retention(returnSomething = "Hello retention test")
    private void performRetention() {
        Log.e("LOG_TAG","Testing annotation 'Annotations_Retention'");
    }

    @Annotation_Documented(writeDocument = "Hello document")
    private void performDocumented() {
        Log.e("LOG_TAG","Testing annotation 'Annotations_Documented'");
    }

}
