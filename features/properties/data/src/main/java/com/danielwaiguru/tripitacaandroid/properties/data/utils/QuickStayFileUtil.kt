package com.danielwaiguru.tripitacaandroid.properties.data.utils

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader


suspend inline fun <reified T> parseJson(
    path: String,
    context: Context,
    kotlinxJson: Json,
    dispatcher: CoroutineDispatcher
): T = withContext(dispatcher){
    val file = context.assets.open(path)
    val bufferedReader = BufferedReader(InputStreamReader(file))
    val stringBuilder = StringBuilder()
    ensureActive()
    bufferedReader.useLines { lines ->
        lines.forEach {
            ensureActive()
            stringBuilder.append(it)
        }
    }
    val jsonString = stringBuilder.toString()
    return@withContext kotlinxJson.decodeFromString(jsonString)
}