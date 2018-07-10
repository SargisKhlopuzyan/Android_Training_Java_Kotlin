package com.example.sargiskh.android_training_java_kotlin.async_task_loader.kotlin

import android.content.Context
import android.os.Bundle
import android.support.v4.content.AsyncTaskLoader
import android.util.Log
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.kotlin.MainActivity_Async_Task_Loader_Kotlin.Companion.BUNDLE_KEY

class AsyncTaskLoaderSubClass(context: Context, val args: Bundle?, val progressInterface: BackgroundProgressInterface) : AsyncTaskLoader<Long>(context){

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): Long {

        Log.e(MainActivity_Async_Task_Loader_Kotlin.TAG, "Inside Load In Background")
        val numberString = args?.getString(BUNDLE_KEY)
        if (numberString == null) {
            Log.e(MainActivity_Async_Task_Loader_Kotlin.TAG, "Bundle = null")
            return 0
        }
        val number = numberString.replace(",", "").toLong()
        var result: Long = 0
        for (i in 1..100) { //loop 100
            result = 0
            for (j in 1..number) {
                result = result + j
            }
            progressInterface.onUpdateProgress(i)
            Log.e(MainActivity_Async_Task_Loader_Kotlin.TAG, "Loop $i Result = $result")
            if (isLoadInBackgroundCanceled) {
                Log.e(MainActivity_Async_Task_Loader_Kotlin.TAG, "Complex Background Computation was Canceled")
                return 0
            }
        }
        return result
    }

}