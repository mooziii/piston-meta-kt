package me.obsilabor.pistonmetakt.annotations

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@RequiresOptIn(
    message = "This is unsafe API functionality and should be used with care.",
    level = RequiresOptIn.Level.WARNING
)
annotation class UnsafePistonMetaApi

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@RequiresOptIn(
    message = "This is unsafe API functionality and currently doesn't work.",
    level = RequiresOptIn.Level.ERROR
)
annotation class NotWorkingPistonMetaApi