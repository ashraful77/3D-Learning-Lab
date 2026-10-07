package com.ashraful.threedlearninglab.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ashraful.threedlearninglab.ui.viewer3d.Learning3DViewer
import com.ashraful.threedlearninglab.ui.viewer3d.ViewerShape

@Composable
fun Prototype3DScene() {
    var shape by remember { mutableStateOf(ViewerShape.Cube) }

    Column(modifier = Modifier.fillMaxSize()) {
        Learning3DViewer(
            modifier = Modifier.weight(1f),
            title = "3D Learning Lab • " + shape.label,
            shape = shape
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ViewerShape.entries.forEach { item ->
                Button(
                    onClick = { shape = item },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(item.label)
                }
            }
        }
    }
}
