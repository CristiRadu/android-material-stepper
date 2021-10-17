package com.stepstone.stepper.sample.test.spoon

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Canvas
import android.os.Looper
import android.util.Log
import java.io.*
import java.util.concurrent.CountDownLatch

/**
 * Copied from [Github](https://github.com/square/spoon/blob/parent-1.2.0/spoon-client/src/main/java/com/squareup/spoon/Spoon.java).
 * Changed the path where to save the screenshots.
 *
 *
 * Utility class for capturing screenshots for Spoon.
 */
object Spoon {
    @SuppressLint("SdCardPath")
    private val REPORTS_DIRECTORY = "/sdcard/material-stepper-screenshots"
    private const val SPOON_SCREENSHOTS = "spoon-screenshots"
    private const val SPOON_FILES = "spoon-files"
    private const val TEST_CASE_CLASS_JUNIT_3 = "android.test.InstrumentationTestCase"
    private const val TEST_CASE_METHOD_JUNIT_3 = "runMethod"
    private const val TEST_CASE_CLASS_JUNIT_4 = "org.junit.runners.model.FrameworkMethod$1"
    private const val TEST_CASE_METHOD_JUNIT_4 = "runReflectiveCall"
    private const val EXTENSION = ".png"
    private const val TAG = "Spoon"

    /**
     * Take a screenshot with the specified tag.  This version allows the caller to manually specify
     * the test class name and method name.  This is necessary when the screenshot is not called in
     * the traditional manner.
     *
     * @param activity Activity with which to capture a screenshot.
     * @param tag      Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    fun screenshot(activity: Activity, tag: String, testClassName: String): File? {
        return try {
            val screenshotDirectory = obtainScreenshotDirectory(testClassName)
            val screenshotName = tag + EXTENSION
            val screenshotFile = File(screenshotDirectory, screenshotName)
            takeScreenshot(screenshotFile, activity)
            Log.d(TAG, "Captured screenshot '$tag'.")
            screenshotFile
        } catch (e: Exception) {
            Log.d(TAG, "Unable to capture screenshot.", e)
            null
            //throw new RuntimeException("Unable to capture screenshot.", e);
        }
    }

    @Throws(IOException::class)
    private fun takeScreenshot(file: File, activity: Activity) {
        val view = activity.window.decorView
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            // On main thread already, Just Do It™.
            drawDecorViewToBitmap(activity, bitmap)
        } else {
            // On a background thread, post to main.
            val latch = CountDownLatch(1)
            activity.runOnUiThread {
                try {
                    drawDecorViewToBitmap(activity, bitmap)
                } finally {
                    latch.countDown()
                }
            }
            try {
                latch.await()
            } catch (e: InterruptedException) {
                val msg = "Unable to get screenshot " + file.absolutePath
                Log.e(TAG, msg, e)
                throw RuntimeException(msg, e)
            }
        }
        var fos: OutputStream? = null
        try {
            fos = BufferedOutputStream(FileOutputStream(file))
            bitmap.compress(CompressFormat.PNG, 100 /* quality */, fos)
//            chmodPlusR(file)
        } finally {
            bitmap.recycle()
            fos?.close()
        }
    }

    private fun drawDecorViewToBitmap(activity: Activity, bitmap: Bitmap) {
        val canvas = Canvas(bitmap)
        activity.window.decorView.draw(canvas)
    }

    @Throws(IllegalAccessException::class)
    private fun obtainScreenshotDirectory(testClassName: String): File {
        return filesDirectory(SPOON_SCREENSHOTS, testClassName)
    }

    /**
     * Alternative to [.save]
     *
     * @param context Context used to access files.
     * @param path    Path to the file you want to save.
     * @return the copy that was created.
     */
    fun save(context: Context?, path: String?): File {
        return save(context, File(path))
    }

    /**
     * Save a file from this test run. The file will be saved under the current class & method.
     * The file will be copied to, so make sure all the data you want have been
     * written to the file before calling save.
     *
     * @param context Context used to access files.
     * @param file    The file to save.
     * @return the copy that was created.
     */
    fun save(context: Context?, file: File): File {
        val testClass = findTestClassTraceElement(Thread.currentThread().stackTrace)
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        val methodName = testClass.methodName
        return save(className, methodName, file)
    }

    private fun save(className: String, methodName: String, file: File): File {
        var filesDirectory: File? = null
        return try {
            filesDirectory = filesDirectory(SPOON_FILES, className)
            if (!file.exists()) {
                throw RuntimeException("Can't find any file at: $file")
            }
            val target = File(filesDirectory, file.name)
            copy(file, target)
            Log.d(TAG, "Saved $file")
            target
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Unable to save file: $file")
        } catch (e: IOException) {
            throw RuntimeException("Couldn't copy file $file to $filesDirectory")
        }
    }

    @Throws(IOException::class)
    private fun copy(source: File, target: File) {
        Log.d(TAG, "Will copy $source to $target")
        target.createNewFile()
//        chmodPlusR(target)
        val `is` = BufferedInputStream(FileInputStream(source))
        val os = BufferedOutputStream(FileOutputStream(target))
        val buffer = ByteArray(4096)
        while (`is`.read(buffer) > 0) {
            os.write(buffer)
        }
        `is`.close()
        os.close()
    }

    @Throws(IllegalAccessException::class)
    private fun filesDirectory(directoryType: String, testClassName: String): File {
        val directory: File
        directory = File(REPORTS_DIRECTORY, "app_$directoryType")
        val dirClass = File(directory, testClassName)
        createDir(dirClass)
        return dirClass
    }

    /**
     * Returns the test class element by looking at the method InstrumentationTestCase invokes.
     */
    fun findTestClassTraceElement(trace: Array<StackTraceElement>): StackTraceElement {
        for (i in trace.indices.reversed()) {
            val element = trace[i]
            if (TEST_CASE_CLASS_JUNIT_3 == element.className && TEST_CASE_METHOD_JUNIT_3 == element.methodName) {
                return trace[i - 3]
            }
            if (TEST_CASE_CLASS_JUNIT_4 == element.className && TEST_CASE_METHOD_JUNIT_4 == element.methodName) {
                return trace[i - 3]
            }
        }
        throw IllegalArgumentException("Could not find test class!")
    }

    @Throws(IllegalAccessException::class)
    private fun createDir(dir: File) {
        val parent = dir.parentFile
        if (!parent.exists()) {
            createDir(parent)
        }
        if (!dir.exists() && !dir.mkdirs()) {
            throw IllegalAccessException("Unable to create output dir: " + dir.absolutePath)
        }
//        chmodPlusRWX(dir)
    }
}