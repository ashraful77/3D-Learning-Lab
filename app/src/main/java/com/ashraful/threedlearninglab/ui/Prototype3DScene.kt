package com.ashraful.threedlearninglab.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import io.github.sceneview.SceneView
import io.github.sceneview.node.CubeNode
import io.github.sceneview.math.Size

@Composable
fun Prototype3DScene() {
    Box(modifier = Modifier.fillMaxSize()) {
        SceneView(modifier = Modifier.fillMaxSize()) {
            CubeNode(size = Size(1.0f))
        }

        Text(
            text = "3D Learning Lab • Cube Prototype",
            modifier = Modifier.align(Alignment.TopCenter),
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
    }
}
