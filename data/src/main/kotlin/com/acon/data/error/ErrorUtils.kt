package com.acon.data.error

import kotlin.coroutines.cancellation.CancellationException

internal inline fun <R> runCatchingWith(
    vararg definedErrors: RootError,
    block: () -> R
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: RemoteError) {
        definedErrors.find { definedError ->
            e.errorCode == definedError.code
        }?.let { Result.failure(it) } ?: Result.failure(e)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

internal inline fun <reified T : RootError> children(): Array<T> {
    val childErrors = mutableListOf<T>()
    val subclasses = T::class.sealedSubclasses
    subclasses.forEach { subclass ->
        val codeProperty = subclass.members.firstOrNull { it.name == "code" }
        val codeValue = codeProperty?.call(subclass.objectInstance ?: subclass.constructors.first().call())

        if (codeValue is Int && codeValue != 0) {
            val errorInstance = subclass.constructors.first().call()
            childErrors.add(errorInstance)
        }
    }
    return childErrors.toTypedArray()
}