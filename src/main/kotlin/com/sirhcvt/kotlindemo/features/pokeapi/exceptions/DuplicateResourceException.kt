package com.sirhcvt.kotlindemo.features.pokeapi.exceptions

class DuplicateResourceException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(
        message: String?,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace)
}