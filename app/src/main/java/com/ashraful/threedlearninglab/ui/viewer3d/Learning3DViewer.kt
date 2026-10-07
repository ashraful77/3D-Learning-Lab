package com.ashraful.threedlearninglab.ui.viewer3d

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sceneview.SceneView
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Size
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.node.CubeNode
import kotlinx.coroutines.delay

@Composable
fun Learning3DViewer(
    title: String = "3D Learning Lab • Cube",
    modifier: Modifier = Modifier,
) {
    var autoRotate by remember { mutableStateOf(false) }
    var rotationY by remember { mutableFloatStateOf(0f) }
    var resetToken by remember { mutableIntStateOf(0) }

    LaunchedEffect(autoRotate) {
        while (autoRotate) {
            rotationY = (rotationY + 1.2f) % 360f
            delay(16L)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        key(resetToken) {
            SceneView(
                modifier = Modifier.fillMaxSize(),
                autoFitContent = true,
                cameraManipulator = rememberCameraManipulator(
                    orbitHomePosition = Position(z = 2.75f),
                    targetPosition = Position()
                )
            ) {
                CubeNode(
                    size = Size(1.0f),
                    rotation = Rotation(y = rotationY)
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(12.dp),
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 4.dp
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp),
            shape = MaterialTheme.shapes.large,
            tonalElevation = 6.dp
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { resetToken++ }) {
                    Text("Reset View")
                }

                Button(onClick = { autoRotate = !autoRotate }) {
                    Text(if (autoRotate) "Stop Rotate" else "Auto Rotate")
                }
            }
        }
    }
}
