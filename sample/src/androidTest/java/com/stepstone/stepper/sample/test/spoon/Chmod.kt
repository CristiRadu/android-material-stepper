//// Copyright 2013 Square, Inc.
//package com.stepstone.stepper.sample.test.spoon
//
//import android.annotation.SuppressLint
//import android.os.Build
//import com.stepstone.stepper.sample.test.spoon.Chmod.Java6Chmod
//import com.stepstone.stepper.sample.test.spoon.Chmod.Java5Chmod
//import java.io.File
//import java.io.IOException
//import java.lang.RuntimeException
//
//internal abstract class Chmod {
//    companion object {
//        private val INSTANCE: Chmod? = null
//        @kotlin.jvm.JvmStatic
//        fun chmodPlusR(file: File) {
//            INSTANCE!!.plusR(file)
//        }
//
//        @kotlin.jvm.JvmStatic
//        fun chmodPlusRWX(file: File) {
//            INSTANCE!!.plusRWX(file)
//        }
//
//        init {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//                INSTANCE = Java6Chmod()
//            } else {
//                INSTANCE = Java5Chmod()
//            }
//        }
//    }
//
//    protected abstract fun plusR(file: File)
//    protected abstract fun plusRWX(file: File)
//    private class Java5Chmod : Chmod() {
//        override fun plusR(file: File) {
//            try {
//                Runtime.getRuntime().exec(arrayOf("chmod", "644", file.absolutePath))
//            } catch (e: IOException) {
//                throw RuntimeException(e)
//            }
//        }
//
//        override fun plusRWX(file: File) {
//            try {
//                Runtime.getRuntime().exec(arrayOf("chmod", "777", file.absolutePath))
//            } catch (e: IOException) {
//                throw RuntimeException(e)
//            }
//        }
//    }
//
//    @SuppressLint("SetWorldReadable", "SetWorldWritable")
//    private class Java6Chmod : Chmod() {
//        override fun plusR(file: File) {
//            file.setReadable(true, false)
//        }
//
//        override fun plusRWX(file: File) {
//            file.setReadable(true, false)
//            file.setWritable(true, false)
//            file.setExecutable(true, false)
//        }
//    }
//}