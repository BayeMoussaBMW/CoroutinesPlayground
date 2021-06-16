import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun produceNumbers(): ReceiveChannel<Any> = produce {
    var x = 1
    while(true){
        send(x++)
    }
}

fun squareNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<Any> = produce {
    for (x in numbers) send(x * x)
}

fun main(args: Array<String>): Unit = runBlocking {
    val producer = produceNumbers()
    val square = squareNumbers(producer as ReceiveChannel<Int>)

    for(i in 1..5) println(square.receive())

    println("main done")

    square.cancel()
    producer.cancel()
}