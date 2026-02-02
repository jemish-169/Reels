package com.practice.reels.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MessagePasser {
    private val _messages = Channel<String>(Channel.BUFFERED)
    val messages = _messages.receiveAsFlow()

    fun sendMessage(msg: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _messages.send(element = msg)
        }
    }
}