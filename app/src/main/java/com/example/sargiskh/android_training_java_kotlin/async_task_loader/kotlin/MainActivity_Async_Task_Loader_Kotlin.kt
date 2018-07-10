package com.example.sargiskh.android_training_java_kotlin.async_task_loader.kotlin

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sargiskh.android_training_java_kotlin.R
import kotlinx.android.synthetic.main.activity_main_async_task_loader_kotlin.*

class MainActivity_Async_Task_Loader_Kotlin : AppCompatActivity(), LoaderManager.LoaderCallbacks<Long>, BackgroundProgressInterface {

    val LOADER_ID = 20 // this can be any integer
    val bundleValue: String = "1,000,000" // 1 Million
    val bundle = Bundle()

    var progressTxtView: TextView? = null
    var finalResultTxtView: TextView? = null

    companion object {
        val TAG = "LOG_TAG"
        val BUNDLE_KEY = "key.to.identify.bundle.value"
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Long> {
        Log.e(TAG, "Inside onCreateLoader, bundle = $args")
        return AsyncTaskLoaderSubClass(this, args, this)
    }

    override fun onLoadFinished(loader: Loader<Long>, data: Long?) {
        Log.e(TAG, "Inside onLoadFinished")
        if (data == null || data < 1) {
            Log.e(TAG, "AsyncTaskLoader = cancelled or Bundle = null")
            return
        } else {
            finalResultTxtView?.setText("The Sum of Numbers between 1 to 1 million \n = $data")
            Log.e(TAG, "Final Result = $data")
        }
    }

    override fun onLoaderReset(loader: Loader<Long>) {}

    override fun onUpdateProgress(progress: Int) {
        runOnUiThread(Runnable {
            kotlin.run {
                progressTxtView?.setText("Progress = $progress%")
            }
        })
//        progressTxtView?.setText("Progress = $progress%")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_async_task_loader_kotlin)

        progressTxtView = findViewById(R.id.progress)
        finalResultTxtView = findViewById(R.id.final_result)

        val beginButton : Button = findViewById(R.id.begin_async_task_loader)
        beginButton.setOnClickListener(View.OnClickListener {
            Log.e(TAG, "Begin Button Tapped!")
            makeOperationAddNumber()
        })

        val cancelButton : Button = findViewById(R.id.cancel_async_task_loader)
        cancelButton.setOnClickListener(View.OnClickListener {
            Log.e(TAG, "Cancel Button Tapped!")
            cancelBackgroundProcess()
        })

        Log.e(TAG, "OnCreate")
        bundle.putString(BUNDLE_KEY, bundleValue)
        supportLoaderManager.initLoader(LOADER_ID, null, this)
    }

    private fun cancelBackgroundProcess() {
        val loader = supportLoaderManager.getLoader<Long>(LOADER_ID)
        if (loader != null) {
            val isCancelled = loader.cancelLoad()
            if (isCancelled) showToastMsg("Loader Canceled!")
        }
    }

    private fun showToastMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun makeOperationAddNumber() {
        // this will try to fetch a Loader with ID = LOADER_ID
        val loader: Loader<Long>? = supportLoaderManager.getLoader(LOADER_ID)
        if (loader == null) {
            /* if the Loader with the loaderID not found,
            * Initialize a New Loader with ID = LOADER_ID
            * Pass in a bundle that the AsynTaskLoader will use
            * Also pass the necessary callback which is 'this' because we've implemented it on our activity
            */
            supportLoaderManager.initLoader(LOADER_ID, bundle, this)
        } else {
            /* If the Loader was found with ID = LOADER_ID,
            * Stop whatever it may be doing
            * Restart it
            * Pass in a bundle that the AsynTaskLoader will use
            * Also pass the necessary callback which is 'this' because we've implemented it on our activity
            */
            supportLoaderManager.restartLoader(LOADER_ID, bundle, this)
        }
    }

}
