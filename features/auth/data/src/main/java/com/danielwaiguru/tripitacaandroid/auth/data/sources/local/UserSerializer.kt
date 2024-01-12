package com.danielwaiguru.tripitacaandroid.auth.data.sources.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.danielwaiguru.tripitacaandroid.auth.data.User
import com.google.protobuf.InvalidProtocolBufferException
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserSerializer @Inject constructor(): Serializer<User> {
    override val defaultValue: User
        get() = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        return try {
            User.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            Timber.e("Unable to read proto.", exception)
            throw CorruptionException("Unable to read proto.", exception)
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }
}