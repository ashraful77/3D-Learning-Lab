package com.ashraful.threedlearninglab.ui.viewer3d

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sceneview.SceneView
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.PathNode
import io.github.sceneview.node.SphereNode
import io.github.sceneview.node.TorusNode
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMainLightNode
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

private data class Planet(
    val name: String,
    val radius: Float,
    val distance: Float,
    val speed: Float,
    val color: Color,
    val hasMoon: Boolean = false,
    val rings: Boolean = false
)

private val planets = listOf(
    Planet("Mercury", 0.32f, 2.0f, 0.040f, Color(0.62f, 0.58f, 0.54f, 1f)),
    Planet("Venus", 0.46f, 2.9f, 0.025f, Color(0.95f, 0.62f, 0.20f, 1f)),
    Planet("Earth", 0.52f, 4.0f, 0.018f, Color(0.12f, 0.38f, 0.92f, 1f), hasMoon = true),
    Planet("Mars", 0.38f, 5.1f, 0.014f, Color(0.82f, 0.28f, 0.10f, 1f)),
    Planet("Jupiter", 1.05f, 7.1f, 0.008f, Color(0.78f, 0.55f, 0.32f, 1f)),
    Planet("Saturn", 0.88f, 9.3f, 0.006f, Color(0.88f, 0.76f, 0.46f, 1f), rings = true),
    Planet("Uranus", 0.65f, 11.5f, 0.004f, Color(0.25f, 0.72f, 0.88f, 1f)),
    Planet("Neptune", 0.62f, 13.3f, 0.003f, Color(0.12f, 0.28f, 0.82f, 1f))
)

private val starPositions = listOf(
    -9f to 7f, -7f to -6f, -5f to 9f, -3f to -8f, 0f to 8f,
    3f to -7f, 5f to 8f, 7f to -5f, 9f to 6f, -10f to -2f,
    -8f to 3f, -6f to -9f, -4f to 5f, 4f to 7f, 6f to 3f,
    8f to -8f, 10f to 1f, -2f to 10f, 2f to -10f, 0f to -8f
)

@Composable
fun SolarSystemViewer(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val materialLoader = rememberMaterialLoader(engine)

    var orbitRunning by remember { mutableStateOf(true) }
    var showOrbits by remember { mutableStateOf(true) }
    var speed by remember { mutableFloatStateOf(1f) }
    var selectedTarget by remember { mutableStateOf("overview") }
    
    val angles = remember {
        MutableList(planets.size) { index -> index * 0.72f }
    }

    LaunchedEffect(orbitRunning, speed) {
        while (orbitRunning) {
            planets.indices.forEach { index ->
                angles[index] = (angles[index] + planets[index].speed * speed) % (Math.PI.toFloat() * 2f)
            }
                        delay(33L)
        }
    }

    val targetPosition = when {
        selectedTarget == "overview" -> Position()
        else -> {
            val index = planets.indexOfFirst { it.name == selectedTarget }
            if (index >= 0) {
                Position(
                    x = cos(angles[index]) * planets[index].distance,
                    z = sin(angles[index]) * planets[index].distance
                )
            } else Position()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        androidx.compose.runtime.key(selectedTarget) {
            SceneView(
                modifier = Modifier.fillMaxSize(),
                engine = engine,
                modelLoader = modelLoader,
                materialLoader = materialLoader,
                autoFitContent = false,
                mainLightNode = rememberMainLightNode(engine) {
                    intensity = 100_000f
                },
                cameraManipulator = rememberCameraManipulator(
                    orbitRadius = if (selectedTarget == "overview") 16f else 4.5f,
                    targetPosition = targetPosition
                )
            ) {
                val sunMaterial = remember(materialLoader) {
                    materialLoader.createColorInstance(
                        Color(1f, 0.34f, 0.02f, 1f),
                        unlit = true
                    )
                }
                val starMaterial = remember(materialLoader) {
                    materialLoader.createColorInstance(
                        Color.White,
                        unlit = true
                    )
                }
                val orbitMaterial = remember(materialLoader) {
                    materialLoader.createColorInstance(
                        Color(0.10f, 0.14f, 0.22f, 1f),
                        unlit = true
                    )
                }

                starPositions.forEach { (x, z) ->
                    SphereNode(
                        radius = 0.035f,
                        stacks = 6,
                        slices = 6,
                        position = Position(x = x, y = 2f, z = z),
                        materialInstance = starMaterial
                    )
                }

                SphereNode(
                    radius = 0.95f,
                    stacks = 32,
                    slices = 32,
                    materialInstance = sunMaterial
                )

                planets.forEachIndexed { index, planet ->
                    val angle = angles[index]
                    val position = Position(
                        x = cos(angle) * planet.distance,
                        z = sin(angle) * planet.distance
                    )

                    val planetMaterial = remember(materialLoader, planet.name) {
                        materialLoader.createColorInstance(
                            planet.color,
                            metallic = 0f,
                            roughness = 0.7f
                        )
                    }

                    if (showOrbits) {
                        val points = remember(planet.distance) {
                            (0..96).map { step ->
                                val theta = step / 96f * Math.PI.toFloat() * 2f
                                Position(
                                    x = cos(theta) * planet.distance,
                                    z = sin(theta) * planet.distance
                                )
                            }
                        }
                        PathNode(
                            points = points,
                            closed = true,
                            materialInstance = orbitMaterial
                        )
                    }

                    SphereNode(
                        radius = planet.radius,
                        stacks = 20,
                        slices = 20,
                        position = position,
                        materialInstance = planetMaterial
                    )

                    if (planet.rings) {
                        TorusNode(
                            majorRadius = planet.radius * 1.65f,
                            minorRadius = 0.08f,
                            rotation = Rotation(x = 25f),
                            position = position,
                            materialInstance = orbitMaterial
                        )
                    }

                    if (planet.hasMoon) {
                        val moonAngle = angle * 5f
                        SphereNode(
                            radius = 0.12f,
                            stacks = 10,
                            slices = 10,
                            position = Position(
                                x = position.x + cos(moonAngle) * 0.9f,
                                y = 0f,
                                z = position.z + sin(moonAngle) * 0.9f
                            ),
                            materialInstance = starMaterial
                        )
                    }
                }
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(12.dp),
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TargetButton("Overview", selectedTarget == "overview") {
                        selectedTarget = "overview"
                    }
                    planets.forEach { planet ->
                        TargetButton(planet.name, selectedTarget == planet.name) {
                            selectedTarget = planet.name
                        }
                    }
                }

                Text(
                    text = if (selectedTarget == "overview") {
                        "SOLAR SYSTEM • Global View"
                    } else {
                        "SOLAR SYSTEM • $selectedTarget"
                    },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(12.dp),
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        orbitRunning = !orbitRunning
                    }) {
                        Text(if (orbitRunning) "Pause Orbit" else "Resume Orbit")
                    }

                    Button(onClick = {
                        showOrbits = !showOrbits
                    }) {
                        Text(if (showOrbits) "Hide Orbits" else "Show Orbits")
                    }

                    Button(onClick = {
                        selectedTarget = "overview"
                    }) {
                        Text("Reset Camera")
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Speed")
                    Slider(
                        value = speed,
                        onValueChange = { speed = it },
                        valueRange = 0.1f..5f,
                        modifier = Modifier.weight(1f)
                    )
                    Text("%.1fx".format(speed))
                }

                Button(onClick = onBack) {
                    Text("← Astronomy")
                }
            }
        }
    }
}

@Composable
private fun TargetButton(
    name: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(name)
    }
}

