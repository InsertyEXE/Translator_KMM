package com.plcoding.translator_kmm


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonFlow<T>(flow: Flow<T>): Flow<T>
fun <T> Flow<T>.toCommonFlow() = CommonFlow(this)

expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>): MutableStateFlow<T>
fun <T> MutableStateFlow<T>.toCommonMutableStateFlow() = CommonMutableStateFlow(this)

expect class CommonStateFlow<T>(flow: StateFlow<T>): StateFlow<T>
fun <T> StateFlow<T>.toCommonStateFlow() = CommonStateFlow(this)