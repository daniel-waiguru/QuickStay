package com.danielwaiguru.tripitacaandroid.properties.data.utils

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader


suspend inline fun <reified T> parseJson(
    path: String,
    context: Context,
    dispatcher: CoroutineDispatcher
): T = withContext(dispatcher){
    val file = context.assets.open(path)
    val bufferedReader = BufferedReader(InputStreamReader(file))
    val stringBuilder = StringBuilder()
    bufferedReader.useLines { lines ->
        ensureActive()
        lines.forEach { stringBuilder.append(it) }
    }
    val jsonString = stringBuilder.toString()
    Timber.d("Json $jsonString")
    return@withContext Gson().fromJson(jsonString, T::class.java)
}