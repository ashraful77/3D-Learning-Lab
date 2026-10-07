package com.ashraful.threedlearninglab.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ashraful.threedlearninglab.ui.viewer3d.Learning3DViewer

@Composable
fun Prototype3DScene() {
    Learning3DViewer(
        modifier = Modifier,
        title = "3D Learning Lab • Cube"
    )
}
