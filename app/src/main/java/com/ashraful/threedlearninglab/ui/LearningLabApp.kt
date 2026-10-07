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

private enum class LabScreen {
    SUBJECTS,
    MATHEMATICS_OBJECTS,
    VIEWER
}

@Composable
fun LearningLabApp() {
    var screen by remember { mutableStateOf(LabScreen.SUBJECTS) }
    var selectedObject by remember { mutableStateOf(Learning3DObjectType.CUBE) }

    when (screen) {
        LabScreen.SUBJECTS -> SubjectMenu(
            onMathematics = { screen = LabScreen.MATHEMATICS_OBJECTS }
        )

        LabScreen.MATHEMATICS_OBJECTS -> ObjectMenu(
            onBack = { screen = LabScreen.SUBJECTS },
            onObjectSelected = {
                selectedObject = it
                screen = LabScreen.VIEWER
            }
        )

        LabScreen.VIEWER -> {
            val name = when (selectedObject) {
                Learning3DObjectType.CUBE -> "Cube"
                Learning3DObjectType.SPHERE -> "Sphere"
                else -> "3D Object"
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = { screen = LabScreen.MATHEMATICS_OBJECTS },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("← Objects")
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
private fun SubjectMenu(
    onMathematics: () -> Unit
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
            "Choose a subject",
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
            style = MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { SubjectCard("Mathematics", onMathematics) }
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
    onBack: () -> Unit,
    onObjectSelected: (Learning3DObjectType) -> Unit
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
            "Choose a 3D object",
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp),
            style = MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                listOf(
                    Learning3DObjectType.CUBE,
                    Learning3DObjectType.SPHERE
                )
            ) { objectType ->
                val name = if (objectType == Learning3DObjectType.CUBE) "Cube" else "Sphere"
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
