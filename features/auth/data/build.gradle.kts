@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("tripitacaandroid.android.library")
    id("tripitacaandroid.android.hilt")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.danielwaiguru.tripitacaandroid.auth.data"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}
dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.datastore.preferences)
    implementation(libs.protobuf.kotlin.lite)
    implementation(projects.shared)
}