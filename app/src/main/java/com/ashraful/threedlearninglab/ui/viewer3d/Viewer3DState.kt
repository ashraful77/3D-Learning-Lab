package com.ashraful.threedlearninglab.ui.viewer3d

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember

@Stable
class Viewer3DState {
    var autoRotate by mutableStateOf(false)
    var rotationY by mutableFloatStateOf(0f)
    var resetToken by mutableIntStateOf(0)

    fun reset() {
        autoRotate = false
        rotationY = 0f
        resetToken++
    }

    fun toggleAutoRotate() {
        autoRotate = !autoRotate
    }
}

@Composable
fun rememberViewer3DState(): Viewer3DState = remember {
    Viewer3DState()
}
