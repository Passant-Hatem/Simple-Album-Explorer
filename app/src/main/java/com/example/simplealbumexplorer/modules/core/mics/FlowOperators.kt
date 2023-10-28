package com.example.simplealbumexplorer.modules.core.mics

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@OptIn(FlowPreview::class)
fun <T> emitFlow(emitter: suspend () -> T): Flow<T> {
    return emitter.asFlow()
}
