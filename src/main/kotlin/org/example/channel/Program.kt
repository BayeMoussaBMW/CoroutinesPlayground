package org.example.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/********Channel ******/

fun productNumbers(): Channel<Int> {
    val channel = Channel<Int>()
    launch{
        for (x in 1..5){
            println("send $x")
            channel.send(x)
        }
        channel.close()
    }
    return channel
}


fun main(args: Array<String>) = runBlocking {
    val channel = productNumbers()

    for(y in channel){
        println("receive: $y")
    }
}
