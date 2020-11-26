package sg.toru.coroutineflow.util

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun View.onClick() = callbackFlow {
    this@onClick.setOnClickListener {
        offer(Unit)
    }
    awaitClose{setOnClickListener(null)}
}