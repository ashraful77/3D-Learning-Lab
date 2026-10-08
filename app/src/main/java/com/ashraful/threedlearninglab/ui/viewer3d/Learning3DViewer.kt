package com.ashraful.threedlearninglab.ui.viewer3d

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.ashraful.threedlearninglab.data.model.Learning3DObject
import com.ashraful.threedlearninglab.data.model.Learning3DObjectType
import io.github.sceneview.SceneView
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.math.Size
import io.github.sceneview.node.ConeNode
import io.github.sceneview.node.CubeNode
import io.github.sceneview.node.CylinderNode
import io.github.sceneview.node.ModelNode
import io.github.sceneview.node.SphereNode
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMainLightNode
import io.github.sceneview.rememberModelInstance
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberOnGestureListener
import kotlinx.coroutines.delay

@Composable
fun Learning3DViewer(
    title: String = "3D Learning Lab • Cube",
    objectType: Learning3DObjectType = Learning3DObjectType.CUBE,
    object: Learning3DObject? = null,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)

    val resolvedObjectType = object?.type ?: objectType

    val modelAsset = when (resolvedObjectType) {
        Learning3DObjectType.MULTIPART_GLB -> "models/multipart-prototype.glb"
        else -> "models/prototype-cube.glb"
    }

    val glbAssetExists = remember(context, resolvedObjectType) {
        if (resolvedObjectType != Learning3DObjectType.GLB && resolvedObjectType != Learning3DObjectType.MULTIPART_GLB) true
        else runCatching {
            context.assets.open(modelAsset).use { }
            true
        }.getOrDefault(false)
    }

    val glbInstance = if (resolvedObjectType == Learning3DObjectType.GLB || resolvedObjectType == Learning3DObjectType.MULTIPART_GLB) {
        rememberModelInstance(modelLoader, modelAsset)
    } else {
        null
    }

    var autoRotate by remember { mutableStateOf(false) }
    var rotationY by remember { mutableFloatStateOf(0f) }
    var resetToken by remember { mutableIntStateOf(0) }
    var loadTimedOut by remember { mutableStateOf(false) }
    var selectedNode by remember { mutableStateOf<String?>(null) }
    var fullscreen by remember { mutableStateOf(false) }

    fun setFullscreen(enabled: Boolean) {
        fullscreen = enabled
        activity?.let { window ->
            val controller = WindowCompat.getInsetsController(window.window, window.window.decorView)
            if (enabled) {
                controller.hide(WindowInsetsCompat.Type.systemBars())
            } else {
                controller.show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    BackHandler(enabled = fullscreen) {
        setFullscreen(false)
    }

    DisposableEffect(activity) {
        onDispose {
            activity?.let { window ->
                WindowCompat.getInsetsController(window.window, window.window.decorView)
                    .show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    LaunchedEffect(resolvedObjectType, glbInstance) {
        loadTimedOut = false
        selectedNode = null
        if ((resolvedObjectType == Learning3DObjectType.GLB || resolvedObjectType == Learning3DObjectType.MULTIPART_GLB) && glbInstance == null) {
            delay(5000L)
            loadTimedOut = true
        }
    }

    LaunchedEffect(autoRotate) {
        while (autoRotate) {
            rotationY = (rotationY + 1.2f) % 360f
            delay(16L)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        SceneView(
            modifier = Modifier.fillMaxSize(),
            engine = engine,
            modelLoader = modelLoader,
            autoFitContent = false,
            onGestureListener = rememberOnGestureListener(
                onSingleTapConfirmed = { _, node ->
                    selectedNode = node?.name
                }
            ),
            mainLightNode = rememberMainLightNode(engine) {
                intensity = 100_000f
            },
            cameraManipulator = rememberCameraManipulator(
                orbitRadius = 3.5f + (resetToken * 0.001f),
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

            when (resolvedObjectType) {
                Learning3DObjectType.GLB, Learning3DObjectType.MULTIPART_GLB -> {
                    glbInstance?.let { instance ->
                        ModelNode(
                            modelInstance = instance,
                            scaleToUnits = 1.0f,
                            rotation = Rotation(y = rotationY),
                            apply = {
                                isTouchable = false
                                name = null
                            }
                        )
                    }
                }

                Learning3DObjectType.SPHERE -> SphereNode(
                    radius = 0.65f,
                    materialInstance = faceMaterials[0],
                    rotation = Rotation(y = rotationY)
                )

                Learning3DObjectType.CYLINDER -> CylinderNode(
                    radius = 0.7f,
                    height = 1.5f,
                    sideCount = 40,
                    materialInstance = faceMaterials[0],
                    rotation = Rotation(y = rotationY)
                )

                Learning3DObjectType.CONE -> ConeNode(
                    radius = 0.9f,
                    height = 1.8f,
                    sideCount = 40,
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

        if (!fullscreen) {
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
        }

        if (!fullscreen && (resolvedObjectType == Learning3DObjectType.GLB || resolvedObjectType == Learning3DObjectType.MULTIPART_GLB)) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 6.dp
            ) {
                Text(
                    text = when {
                        !glbAssetExists -> "GLB asset missing from APK"
                        glbInstance != null -> if (resolvedObjectType == Learning3DObjectType.MULTIPART_GLB) "Multi-part GLB loaded" else "GLB loaded"
                        loadTimedOut -> "GLB asset found, but model failed to load"
                        else -> "Loading GLB…"
                    },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        if (!fullscreen && selectedNode != null) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 110.dp),
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 8.dp
            ) {
                Text(
                    text = "✓ Selected part: $selectedNode",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
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
                Button(onClick = {
                    autoRotate = false
                    rotationY = 0f
                    resetToken++
                }) {
                    Text("Reset View")
                }

                Button(onClick = { autoRotate = !autoRotate }) {
                    Text(if (autoRotate) "Stop Rotate" else "Auto Rotate")
                }

                Button(onClick = { setFullscreen(!fullscreen) }) {
                    Text(if (fullscreen) "Exit Fullscreen" else "Fullscreen")
                }
            }
        }
    }
}

private fun Context.findActivity(): Activity? {
    var current = this
    while (current is ContextWrapper) {
        if (current is Activity) return current
        current = current.baseContext
    }
    return current as? Activity
}
