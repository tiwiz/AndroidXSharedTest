package net.orgiu.tests.biometric

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.Executor
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
        callback())

fun callback() = object : BiometricPrompt.AuthenticationCallback() {
    override fun onAuthenticationError(errorCode: Int,
            errString: CharSequence) {
        /** DO THINGS **/
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        /** DO THINGS **/
    }

    override fun onAuthenticationFailed() {
        /** DO THINGS **/
    }
}

fun authanticate(activity: AppCompatActivity, val executor: Executor) {
    val biometricPrompt = BiometricPrompt(
        activity,
        executor,
        callback())
}

suspend fun AppCompatActivity.authenticate(
    info: BiometricPrompt.PromptInfo,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
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
        ).apply {
            continuation.invokeOnCancellation {
                cancelAuthentication()
            }
        }.authenticate(info)
    }

sealed class BiometricException : Exception()
data class BiometricError(val errorCode: Int, val errorMessage: String) : BiometricException()
class BiometricFailure : BiometricException()

fun buildPromptInfo() =
    BiometricPrompt.PromptInfo
        .Builder()
        .setTitle("Biometric authentication sample.")
        .setSubtitle("I'm a subtitle.")
        .setDescription("I'm a description")
        .setNegativeButtonText("Cancel")
        .build()
