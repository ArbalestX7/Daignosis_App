package com.daignosis.daignosis.data.socket

import android.content.ContentValues
import android.util.Log
import com.daignosis.daignosis.utils.Util.base_Url
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {
    var mSocket: Socket? = null
    //lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
// "http://10.0.2.2:3000" is the network your Android emulator must use to join the localhost network on your computer
// "http://localhost:3000/" will not work
// If you want to use your physical phone you could use your ip address plus :3000
// This will allow your Android Emulator and physical device at your home to connect to the server
            mSocket = IO.socket(base_Url)
        } catch (e: URISyntaxException) {
            Log.d(ContentValues.TAG, "setSocket: ${e.message.toString()}")
        }
    }

    @Synchronized
    fun getSocket(): Socket? {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket?.connect()
        Log.d(ContentValues.TAG, "establishConnection: Connected")
    }

    @Synchronized
    fun closeConnection() {
        mSocket?.disconnect()
        Log.d(ContentValues.TAG, "closeConnection: Disconnected")
    }
}