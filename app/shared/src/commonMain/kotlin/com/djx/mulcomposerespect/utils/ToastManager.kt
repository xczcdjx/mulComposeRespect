package com.djx.mulcomposerespect.utils

import com.dokar.sonner.ToastType
import com.dokar.sonner.ToasterDefaults
import com.dokar.sonner.ToasterState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration


import kotlin.time.TimeSource

private val appStartTime = TimeSource.Monotonic.markNow()

fun currentNanoTime(): Long {
    return appStartTime.elapsedNow().inWholeNanoseconds
}
data class ToastEvent(
    val message: Any,
    val type: ToastType = ToastType.Normal,
    val id: Any = currentNanoTime(),
    val icon: Any? = null,
//    val action: Any? = null,
    val actionBuilder: ((ToasterState) -> Any?)? = null,
    val duration: Duration = ToasterDefaults.DurationDefault
)
sealed class ToastAction {
    data object None : ToastAction()

    data class Dismiss(
        val text: String = "关闭"
    ) : ToastAction()
}
object ToastManager {
    private val _toast = MutableSharedFlow<ToastEvent>(
        extraBufferCapacity = 1
    )
    val toast = _toast.asSharedFlow()

    suspend fun show(
        message: Any,
        type: ToastType = ToastType.Normal,
        id: Any = currentNanoTime(),
        icon: Any? = null,
        actionBuilder: ((ToasterState) -> Any?)? = null,
        duration: Duration = ToasterDefaults.DurationDefault
    ) {
        _toast.emit(
            ToastEvent(
                message = message,
                type = type,
                id = id,
                icon = icon,
                actionBuilder = actionBuilder,
                duration = duration
            )
        )
    }

    fun showCompose(
        message: Any,
        type: ToastType = ToastType.Normal,
        id: Any = currentNanoTime(),
        icon: Any? = null,
        actionBuilder: ((ToasterState) -> Any?)? = null,
        duration: Duration = ToasterDefaults.DurationDefault
    ) {
        _toast.tryEmit(
            ToastEvent(
                message = message,
                type = type,
                id = id,
                icon = icon,
                actionBuilder = actionBuilder,
                duration = duration
            )
        )
    }
}