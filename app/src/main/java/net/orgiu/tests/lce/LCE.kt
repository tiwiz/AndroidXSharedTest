package net.orgiu.tests.lce

sealed class LCE<out T> {

    open val data: T? = null

    class Complete<out T>(override val data: T) : LCE<T>()

    class Error(val error: Throwable) : LCE<Nothing>()

    object Loading : LCE<Nothing>()
}