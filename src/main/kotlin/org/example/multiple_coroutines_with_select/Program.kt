package org.example.multiple_coroutines_with_select

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun producer1() = produce {
    while(true) {
        delay(200)
        send("from producer 1")
    }
}

fun producer2() = produce {
    while(true) {
        delay(200)
        send("from producer 2")
    }
}

suspend fun selector(message1: ReceiveChannel<String>, message2: ReceiveChannel<String>) {
    select<Unit> {
        message1.onReceive {
                value -> println(value)
        }
        message2.onReceive {
                value -> println(value)
        }
    }
}


fun main(args: Array<String>) = runBlocking<Unit> {
    val m1 = producer1()
    val m2 = producer2()

    repeat(15) {
        selector(m1, m2)
    }
}

