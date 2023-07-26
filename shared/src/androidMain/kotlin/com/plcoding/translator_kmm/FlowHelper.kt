package com.plcoding.translator_kmm

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

actual class CommonFlow<T> actual constructor(
    private val flow: Flow<T>
) : Flow<T> by flow

actual class CommonMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>
) : MutableStateFlow<T> by flow

actual class CommonStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
) : StateFlow<T> by flow