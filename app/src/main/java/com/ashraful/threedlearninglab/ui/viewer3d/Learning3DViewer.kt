package com.ashraful.threedlearninglab.ui.viewer3d

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ashraful.threedlearninglab.data.model.Learning3DObjectType
import io.github.sceneview.SceneView
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Size
import io.github.sceneview.node.CubeNode
import io.github.sceneview.node.ModelNode
import io.github.sceneview.node.SphereNode
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberModelInstance
import kotlinx.coroutines.delay

@Composable
fun Learning3DViewer(
    title: String = "3D Learning Lab • Cube",
    objectType: Learning3DObjectType = Learning3DObjectType.CUBE,
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
                autoFitContent = false,
                cameraManipulator = rememberCameraManipulator(
                    orbitHomePosition = Position(z = 4.0f),
                    targetPosition = Position()
                )
            ) {
                val faceColors = listOf(
                    Color(0.20f, 0.55f, 0.95f, 1f),
                    Color(0.95f, 0.35f, 0.35f, 1f),
                    Color(0.25f, 0.75f, 0.45f, 1f),
                    Color(0.98f, 0.72f, 0.20f, 1f),
                    Color(0.65f, 0.40f, 0.90f, 1f),
                    Color(0.20f, 0.75f, 0.80f, 1f)
                )

                val faceMaterials = faceColors.map { color ->
                    remember(materialLoader, color) {
                        materialLoader.createColorInstance(
                            color = color,
                            metallic = 0f,
                            roughness = 0.6f
                        )
                    }
                }

                when (objectType) {
                    Learning3DObjectType.GLB -> {
                        rememberModelInstance(
                            modelLoader,
                            "models/prototype-cube.glb"
                        )?.let { instance ->
                            ModelNode(
                                modelInstance = instance,
                                scaleToUnits = 1.0f,
                                rotation = Rotation(y = rotationY)
                            )
                        }
                    }

                    Learning3DObjectType.SPHERE -> SphereNode(
                        radius = 0.65f,
                        materialInstance = faceMaterials[0],
                        rotation = Rotation(y = rotationY)
                    )

                    else -> CubeNode(
                        size = Size(1.0f),
                        materialInstance = faceMaterials[0],
                        rotation = Rotation(y = rotationY),
                        apply = {
                            for (index in 1 until faceMaterials.size) {
                                setMaterialInstanceAt(index, faceMaterials[index])
                            }
                        }
                    )
                }
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
                .navigationBarsPadding()
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
