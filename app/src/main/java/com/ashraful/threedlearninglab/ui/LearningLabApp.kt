package com.ashraful.threedlearninglab.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ashraful.threedlearninglab.data.model.Learning3DObjectType
import com.ashraful.threedlearninglab.ui.viewer3d.Learning3DViewer
import com.ashraful.threedlearninglab.ui.viewer3d.GeometryStudioViewer
import com.ashraful.threedlearninglab.ui.viewer3d.SolarSystemViewer

private enum class LabArea {
    LIBRARY,
    EXPERIMENTS
}

private enum class LabScreen {
    HOME,
    LIBRARY_SUBJECTS,
    MATHEMATICS_OBJECTS,
    EXPERIMENT_LIST,
    SOLAR_SYSTEM,
    GEOMETRY_STUDIO,
    VIEWER
}

private enum class LibrarySubject {
    MATHEMATICS,
    ASTRONOMY
}

@Composable
fun LearningLabApp() {
    var screen by remember { mutableStateOf(LabScreen.HOME) }
    var selectedArea by remember { mutableStateOf(LabArea.LIBRARY) }
    var selectedSubject by remember { mutableStateOf(LibrarySubject.MATHEMATICS) }
    var selectedObject by remember { mutableStateOf(Learning3DObjectType.CUBE) }

    when (screen) {
        LabScreen.HOME -> HomeMenu(
            onLibrary = {
                selectedArea = LabArea.LIBRARY
                screen = LabScreen.LIBRARY_SUBJECTS
            },
            onExperiments = {
                selectedArea = LabArea.EXPERIMENTS
                screen = LabScreen.EXPERIMENT_LIST
            }
        )

        LabScreen.LIBRARY_SUBJECTS -> LibrarySubjectMenu(
            onBack = { screen = LabScreen.HOME },
            onMathematics = {
                selectedSubject = LibrarySubject.MATHEMATICS
                screen = LabScreen.MATHEMATICS_OBJECTS
            },
            onAstronomy = {
                selectedSubject = LibrarySubject.ASTRONOMY
                screen = LabScreen.SOLAR_SYSTEM
            }
        )

        LabScreen.MATHEMATICS_OBJECTS -> GeometryCategoryMenu(
            onBack = { screen = LabScreen.LIBRARY_SUBJECTS },
            onGeometry = { screen = LabScreen.GEOMETRY_STUDIO }
        )

        LabScreen.GEOMETRY_STUDIO -> GeometryStudioViewer(
            onBack = { screen = LabScreen.MATHEMATICS_OBJECTS }
        )

        LabScreen.EXPERIMENT_LIST -> ExperimentMenu(
            onBack = { screen = LabScreen.HOME },
            onExperimentSelected = {
                selectedObject = it
                screen = LabScreen.VIEWER
            }
        )

        LabScreen.SOLAR_SYSTEM -> SolarSystemViewer(
            onBack = { screen = LabScreen.LIBRARY_SUBJECTS }
        )

        LabScreen.VIEWER -> {
            val name = when (selectedObject) {
                Learning3DObjectType.CUBE -> "Cube"
                Learning3DObjectType.SPHERE -> "Sphere"
                Learning3DObjectType.MULTIPART_GLB -> "Multi-Part Test"
                else -> "3D Object"
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = {
                        screen = when (selectedArea) {
                            LabArea.LIBRARY -> LabScreen.MATHEMATICS_OBJECTS
                            LabArea.EXPERIMENTS -> LabScreen.EXPERIMENT_LIST
                        }
                    },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("← Back")
                }

                Learning3DViewer(
                    modifier = Modifier.weight(1f),
                    title = "3D Learning Lab • $name",
                    objectType = selectedObject
                )
            }
        }
    }
}

@Composable
private fun HomeMenu(
    onLibrary: () -> Unit,
    onExperiments: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "3D Learning Lab",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Interactive 3D teaching laboratory",
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp),
            style = MaterialTheme.typography.titleMedium
        )

        SectionCard(
            title = "📚 3D Object Library",
            description = "Educational 3D objects organized by subject.",
            onClick = onLibrary
        )

        SectionCard(
            title = "🧪 3D Experiment Lab",
            description = "Prototype and test new 3D interactions.",
            onClick = onExperiments,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
private fun SectionCard(
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(
                description,
                modifier = Modifier.padding(top = 6.dp, bottom = 14.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Open")
            }
        }
    }
}

@Composable
private fun LibrarySubjectMenu(
    onBack: () -> Unit,
    onMathematics: () -> Unit,
    onAstronomy: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = onBack) {
            Text("← Home")
        }

        Text(
            "3D Object Library",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Choose a subject",
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
            style = MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { SubjectCard("Mathematics", onMathematics) }
            item { SubjectCard("Astronomy", onAstronomy) }
            item { SubjectCard("Physics") }
            item { SubjectCard("Chemistry") }
            item { SubjectCard("Biology") }
            item { SubjectCard("Others") }
        }
    }
}

@Composable
private fun SubjectCard(
    name: String,
    onClick: () -> Unit = {}
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            Text(name)
        }
    }
}


@Composable
private fun GeometryCategoryMenu(
    onBack: () -> Unit,
    onGeometry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = onBack) {
            Text("← Subjects")
        }

        Text(
            "Mathematics",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Choose a category",
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Geometry", style = MaterialTheme.typography.titleLarge)
                Text(
                    "Interactive 3D Geometry Studio",
                    modifier = Modifier.padding(top = 6.dp, bottom = 14.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    onClick = onGeometry,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Open Geometry Studio")
                }
            }
        }
    }
}

@Composable
private fun ObjectMenu(
    subject: LibrarySubject,
    onBack: () -> Unit,
    onObjectSelected: (Learning3DObjectType) -> Unit
) {
    val title = when (subject) {
        LibrarySubject.MATHEMATICS -> "Mathematics"
        LibrarySubject.ASTRONOMY -> "Astronomy"
    }

    val objects = when (subject) {
        LibrarySubject.MATHEMATICS -> listOf(
            Learning3DObjectType.CUBE,
            Learning3DObjectType.SPHERE
        )
        LibrarySubject.ASTRONOMY -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = onBack) {
            Text("← Subjects")
        }

        Text(
            title,
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            when (subject) {
                LibrarySubject.MATHEMATICS -> "Geometry"
                LibrarySubject.ASTRONOMY -> "Coming next: Solar System"
            },
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
            style = MaterialTheme.typography.titleMedium
        )

        if (objects.isEmpty()) {
            Text(
                "The Solar System experience is being migrated from the supplied interactive HTML into the offline Android 3D library.",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(objects) { objectType ->
                    val name = when (objectType) {
                        Learning3DObjectType.CUBE -> "Cube"
                        Learning3DObjectType.SPHERE -> "Sphere"
                        else -> "3D Object"
                    }
                    Card {
                        Button(
                            onClick = { onObjectSelected(objectType) },
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(vertical = 28.dp)
                        ) {
                            Text(name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExperimentMenu(
    onBack: () -> Unit,
    onExperimentSelected: (Learning3DObjectType) -> Unit
) {
    val experiments = listOf(
        Learning3DObjectType.MULTIPART_GLB
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = onBack) {
            Text("← Home")
        }

        Text(
            "3D Experiment Lab",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "Technical prototypes and validation tests",
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
            style = MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(experiments) { objectType ->
                Card {
                    Button(
                        onClick = { onExperimentSelected(objectType) },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 28.dp)
                    ) {
                        Text("Multi-Part Test")
                    }
                }
            }
        }
    }
}
