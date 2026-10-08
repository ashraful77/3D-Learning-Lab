package com.ashraful.threedlearninglab.ui.viewer3d

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sceneview.SceneView
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Size
import io.github.sceneview.node.CapsuleNode
import io.github.sceneview.node.ConeNode
import io.github.sceneview.node.CubeNode
import io.github.sceneview.node.CylinderNode
import io.github.sceneview.node.SphereNode
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMainLightNode
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import kotlinx.coroutines.delay

private enum class GeometryShape(val title: String, val info: String) {
    CUBE("Cube", "2 × 2 × 2 units • 6 faces"),
    SPHERE("Sphere", "Radius 1.4 units • curved surface"),
    CYLINDER("Cylinder", "Radius 1.0 • height 2.4"),
    CONE("Cone", "Base radius 1.3 • height 2.6"),
    PYRAMID("Pyramid", "Square base • height 2.4"),
    TORUS("Torus", "Major radius 1.2 • tube radius 0.45"),
    TORUS_KNOT("Torus Knot", "Parametric (2,3) knot"),
    CAPSULE("Capsule", "Radius 0.8 • cylinder height 1.4")
}

private val geometryColors = listOf(
    Color(0.23f, 0.51f, 0.96f, 1f),
    Color(0.10f, 0.72f, 0.55f, 1f),
    Color(0.94f, 0.40f, 0.25f, 1f),
    Color(0.72f, 0.42f, 0.92f, 1f),
    Color(0.92f, 0.70f, 0.20f, 1f)
)

@Composable
fun GeometryStudioViewer(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val materialLoader = rememberMaterialLoader(engine)

    var shape by remember { mutableStateOf(GeometryShape.CUBE) }
    var autoRotate by remember { mutableStateOf(true) }
    var rotationY by remember { mutableFloatStateOf(0f) }
    var resetToken by remember { mutableIntStateOf(0) }
    var colorIndex by remember { mutableIntStateOf(0) }
    var showDimensions by remember { mutableStateOf(true) }

    LaunchedEffect(autoRotate) {
        while (autoRotate) {
            rotationY = (rotationY + 1.2f) % 360f
            delay(33L)
        }
    }

    val material = remember(materialLoader, colorIndex) {
        materialLoader.createColorInstance(
            geometryColors[colorIndex],
            metallic = 0.15f,
            roughness = 0.38f
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        SceneView(
            modifier = Modifier.fillMaxSize(),
            engine = engine,
            modelLoader = modelLoader,
            materialLoader = materialLoader,
            autoFitContent = false,
            mainLightNode = rememberMainLightNode(engine) { intensity = 100_000f },
            cameraManipulator = rememberCameraManipulator(
                orbitRadius = 7.0f + resetToken * 0.001f,
                targetPosition = Position()
            )
        ) {
            when (shape) {
                GeometryShape.CUBE -> CubeNode(
                    size = Size(2f, 2f, 2f),
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.SPHERE -> SphereNode(
                    radius = 1.4f,
                    stacks = 32,
                    slices = 32,
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.CYLINDER -> CylinderNode(
                    radius = 1f,
                    height = 2.4f,
                    sideCount = 40,
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.CONE -> ConeNode(
                    radius = 1.3f,
                    height = 2.6f,
                    sideCount = 40,
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.PYRAMID -> ConeNode(
                    radius = 1.6f,
                    height = 2.4f,
                    sideCount = 4,
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.TORUS -> TorusNode(
                    majorRadius = 1.2f,
                    minorRadius = 0.45f,
                    majorSegments = 48,
                    minorSegments = 24,
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.CAPSULE -> CapsuleNode(
                    radius = 0.8f,
                    height = 1.4f,
                    capStacks = 12,
                    sideSlices = 32,
                    rotation = Rotation(y = rotationY),
                    materialInstance = material
                )
                GeometryShape.TORUS_KNOT -> TorusNode(
                    majorRadius = 1.05f,
                    minorRadius = 0.30f,
                    majorSegments = 48,
                    minorSegments = 20,
                    rotation = Rotation(x = 55f, y = rotationY),
                    materialInstance = material
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(0.96f)
                .padding(top = 10.dp, start = 8.dp, end = 8.dp),
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                GeometryShape.values().forEach { item ->
                    FilterChip(
                        selected = item == shape,
                        onClick = { shape = item },
                        label = { Text(item.title, maxLines = 1) }
                    )
                }
            }
        }

        if (showDimensions) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 76.dp, end = 12.dp),
                shape = MaterialTheme.shapes.small,
                tonalElevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        "DIMENSIONS",
                        style = MaterialTheme.typography.titleSmall
                    )
                    dimensionLines(shape).forEach { line ->
                        Text(
                            line,
                            modifier = Modifier.padding(top = 3.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 76.dp, start = 12.dp),
            shape = MaterialTheme.shapes.small,
            tonalElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    "GEOMETRY • " + shape.title.uppercase(),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    shape.info,
                    modifier = Modifier.padding(top = 3.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.96f)
                .navigationBarsPadding()
                .padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 6.dp),
                        onClick = { autoRotate = !autoRotate }
                    ) {
                        Text(
                            if (autoRotate) "Auto Rotate: On" else "Auto Rotate: Off",
                            maxLines = 1,
                            fontSize = 11.sp
                        )
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 6.dp),
                        onClick = {
                            rotationY = 0f
                            resetToken++
                        }
                    ) {
                        Text("Reset View", maxLines = 1, fontSize = 12.sp)
                    }
                    FilterChip(
                        selected = showDimensions,
                        onClick = { showDimensions = !showDimensions },
                        label = { Text("Dimensions", fontSize = 11.sp) }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    geometryColors.forEachIndexed { index, _ ->
                        FilterChip(
                            selected = colorIndex == index,
                            onClick = { colorIndex = index },
                            label = { Text("Color " + (index + 1), fontSize = 11.sp) }
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    onClick = onBack
                ) {
                    Text("← Mathematics", maxLines = 1)
                }
            }
        }
    }
}
private fun dimensionLines(shape: GeometryShape): List<String> = when (shape) {
    GeometryShape.CUBE -> listOf("Length: 2.0", "Width: 2.0", "Height: 2.0")
    GeometryShape.SPHERE -> listOf("Radius: 1.4", "Diameter: 2.8")
    GeometryShape.CYLINDER -> listOf("Radius: 1.0", "Height: 2.4")
    GeometryShape.CONE -> listOf("Base radius: 1.3", "Height: 2.6")
    GeometryShape.PYRAMID -> listOf("Base radius: 1.6", "Height: 2.4", "Sides: 4")
    GeometryShape.TORUS -> listOf("Major radius: 1.2", "Tube radius: 0.45")
    GeometryShape.TORUS_KNOT -> listOf("Major radius: 1.05", "Tube radius: 0.30", "Type: (2,3)")
    GeometryShape.CAPSULE -> listOf("Radius: 0.8", "Cylinder height: 1.4")
}
