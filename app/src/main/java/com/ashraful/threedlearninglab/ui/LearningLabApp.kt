package com.ashraful.threedlearninglab.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.LazyColumn
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
import com.ashraful.threedlearninglab.data.model.Learning3DCapability
import com.ashraful.threedlearninglab.data.model.Learning3DObjectType
import com.ashraful.threedlearninglab.data.model.Learning3DObject
import com.ashraful.threedlearninglab.data.repository.Learning3DRepository
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
    OBJECT_DETAILS,
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
    var selectedLibraryObject by remember { mutableStateOf<Learning3DObject?>(null) }

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

        LabScreen.MATHEMATICS_OBJECTS -> ObjectMenu(
            subject = selectedSubject,
            onBack = { screen = LabScreen.LIBRARY_SUBJECTS },
            onGeometryStudio = { screen = LabScreen.GEOMETRY_STUDIO },
            onObjectSelected = {
                selectedLibraryObject = it
                selectedObject = it.type
                screen = LabScreen.OBJECT_DETAILS
            }
        )

        LabScreen.GEOMETRY_STUDIO -> GeometryStudioViewer(
            onBack = { screen = LabScreen.MATHEMATICS_OBJECTS }
        )

        LabScreen.OBJECT_DETAILS -> {
            selectedLibraryObject?.let { objectItem ->
                ObjectDetails(
                    objectItem = objectItem,
                    onBack = { screen = LabScreen.MATHEMATICS_OBJECTS },
                    onOpenViewer = { screen = LabScreen.VIEWER }
                )
            }
        }

        LabScreen.EXPERIMENT_LIST -> ExperimentMenu(
            onBack = { screen = LabScreen.HOME },
            onExperimentSelected = {
                selectedLibraryObject = null
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
                    title = "3D Learning Lab • " + (selectedLibraryObject?.name ?: name),
                    objectType = selectedLibraryObject?.type ?: selectedObject,
                    libraryObject = selectedLibraryObject
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
private fun ObjectMenu(
    subject: LibrarySubject,
    onBack: () -> Unit,
    onGeometryStudio: () -> Unit,
    onObjectSelected: (Learning3DObject) -> Unit
) {
    val subjectId = when (subject) {
        LibrarySubject.MATHEMATICS -> "mathematics"
        LibrarySubject.ASTRONOMY -> "astronomy"
    }
    val objects = Learning3DRepository.getBySubject(subjectId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onClick = onBack) { Text("← Subjects") }

        Text(
            if (subject == LibrarySubject.MATHEMATICS) "Mathematics" else "Astronomy",
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            "3D Objects",
            modifier = Modifier.padding(top = 6.dp, bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(objects) { objectItem ->
                Card {
                    Button(
                        onClick = { onObjectSelected(objectItem) },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 24.dp)
                    ) {
                        Text(objectItem.name)
                    }
                }
            }
        }

        if (subject == LibrarySubject.MATHEMATICS) {
            Button(
                onClick = onGeometryStudio,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text("Open Geometry Studio")
            }
        }
    }
}

@Composable
private fun ObjectDetails(
    objectItem: Learning3DObject,
    onBack: () -> Unit,
    onOpenViewer: () -> Unit
) {
    val info = objectItem.metadata.educationalInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Button(onClick = onBack, modifier = Modifier.padding(top = 12.dp)) {
            Text("← 3D Objects")
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(objectItem.name, style = MaterialTheme.typography.headlineMedium)
                Text(
                    objectItem.description,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            item { InfoCard("Class Levels", objectItem.metadata.classLevels.joinToString(" • ")) }
            item { InfoCard("Definition", info.definition) }
            if (info.keyPoints.isNotEmpty()) item { BulletCard("Key Points", info.keyPoints) }
            if (objectItem.parts.isNotEmpty()) {
                item {
                    BulletCard("Parts", objectItem.parts.map { "${it.name}: ${it.description}" })
                }
            }
            if (info.formulas.isNotEmpty()) item { BulletCard("Formulas", info.formulas) }
            if (info.teacherTips.isNotEmpty()) item { BulletCard("Teacher Tips", info.teacherTips) }
            if (info.discussionQuestions.isNotEmpty()) {
                item { BulletCard("Discussion Questions", info.discussionQuestions) }
            }
            if (info.realWorldApplications.isNotEmpty()) {
                item { BulletCard("Real-World Applications", info.realWorldApplications) }
            }
            item {
                InfoCard(
                    "Capabilities",
                    objectItem.capabilities.sortedBy { it.name }.joinToString(" • ") { capabilityLabel(it) }
                )
            }
            item { InfoCard("Tags", objectItem.metadata.tags.joinToString(" • ")) }
        }

        Button(
            onClick = onOpenViewer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Text("Open 3D Viewer")
        }
    }
}

@Composable
private fun InfoCard(title: String, text: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(
                text.ifBlank { "Not available yet." },
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun BulletCard(title: String, items: List<String>) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            items.forEach { item ->
                Text(
                    "• $item",
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

private fun capabilityLabel(capability: Learning3DCapability): String =
    when (capability) {
        Learning3DCapability.ROTATE -> "Rotate"
        Learning3DCapability.ZOOM -> "Zoom"
        Learning3DCapability.PAN -> "Pan"
        Learning3DCapability.RESET -> "Reset"
        Learning3DCapability.AUTO_ROTATE -> "Auto Rotate"
        Learning3DCapability.SELECTION -> "Selection"
        Learning3DCapability.LABELS -> "Labels"
        Learning3DCapability.DIMENSIONS -> "Dimensions"
        Learning3DCapability.ANIMATION -> "Animation"
        Learning3DCapability.CUTAWAY -> "Cutaway"
        Learning3DCapability.NET_UNFOLD -> "Net Unfold"
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
