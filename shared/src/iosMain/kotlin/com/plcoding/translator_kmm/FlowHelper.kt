package com.plcoding.translator_kmm

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow


fun interface DisposableHandle: kotlinx.coroutines.DisposableHandle

actual open class CommonFlow<T> actual constructor(
    private val flow: Flow<T>
): Flow<T> by flow {

    fun subscribe(
        coroutineScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): DisposableHandle {
        val job = coroutineScope.launch(dispatcher) {
            flow.collect(onCollect)
        }
        return DisposableHandle { job.cancel() }
    }
}

actual open class CommonMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>
): CommonStateFlow<T>(flow), MutableStateFlow<T> {

    override var value: T
        get() = super.value
        set(value) {
            flow.value = value
        }

    override val subscriptionCount: StateFlow<Int>
        get() = flow.subscriptionCount

    override fun compareAndSet(expect: T, update: T): Boolean {
        return flow.compareAndSet(expect, update)
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        flow.resetReplayCache()
    }

    override fun tryEmit(value: T): Boolean {
        return flow.tryEmit(value)
    }

    override suspend fun emit(value: T) {
        flow.emit(value)
    }
}

actual open class CommonStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
): CommonFlow<T>(flow), StateFlow<T> {
    override val replayCache: List<T>
        get() = flow.replayCache
    override val value: T
        get() = flow.value

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        flow.collect(collector)
    }
}

class IOSMutableStateFlow<T>(
    initialValue: T
): CommonMutableStateFlow<T>(MutableStateFlow(initialValue))