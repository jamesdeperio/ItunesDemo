package jamesdeperio.github.itunesdemo.util

import android.util.MalformedJsonException
import java.io.EOFException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


object NetworkUtil {

    fun isNetworkError(throwable: Throwable?): Boolean {
        if(throwable!= null && !(throwable is EOFException) && !(throwable is MalformedJsonException)) {
            return throwable is SocketTimeoutException ||
            throwable is SSLHandshakeException ||
            throwable is UnknownHostException ||
            throwable is InterruptedIOException ||
            throwable is IOException
        }
        return false
    }
}