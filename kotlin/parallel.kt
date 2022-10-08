import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

@OptIn(ExperimentalCoroutinesApi::class)
fun main():Unit = runBlocking{
    val channel = aboutProducer()

    for(each in channel){
        println("Receiving .... $each")
        if(channel.isClosedForReceive) println("Done")
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.aboutProducer()= produce<Any> {
    repeat(5) {
        delay(2000)
        send(it)
    }
}

suspend fun <T> kb(block: suspend ()->T): T = coroutineScope {
    block()
}