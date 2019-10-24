package net.orgiu.tests.biometric

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun AppCompatActivity.buildBiometricPrompt(
    success: () -> Unit,
    error: (Int, String) -> Unit,
    failure: () -> Unit,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) =
    BiometricPrompt(
        this,
        dispatcher.asExecutor(),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                error(errorCode, errString.toString())
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                success()
            }

            override fun onAuthenticationFailed() {
                failure()
            }
        })

suspend fun AppCompatActivity.authenticate(
    info: BiometricPrompt.PromptInfo,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) =
    suspendCancellableCoroutine<Unit> { continuation ->
        buildBiometricPrompt(
            success = { continuation.resume(Unit) },
            error = { code, message ->
                continuation.resumeWithException(
                    BiometricError(
                        code,
                        message
                    )
                )
            },
            failure = { continuation.resumeWithException(BiometricFailure()) },
            dispatcher = dispatcher
        ).authenticate(info)
    }

sealed class BiometricException : Exception()
data class BiometricError(val errorCode: Int, val errorMessage: String) : BiometricException()
class BiometricFailure : BiometricException()

fun buildPromptInfo() = BiometricPrompt.PromptInfo.Builder()
    .setTitle("Biometric authentication sample.")
    .setSubtitle("I'm a subtitle.")
    .setDescription("I'm a description")
    .setNegativeButtonText("Cancel")
    .build()