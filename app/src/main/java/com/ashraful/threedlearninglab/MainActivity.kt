package com.ashraful.threedlearninglab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ashraful.threedlearninglab.data.model.Learning3DObjectType
import com.ashraful.threedlearninglab.ui.viewer3d.Learning3DViewer
import com.ashraful.threedlearninglab.ui.theme.ThreeDLearningLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThreeDLearningLabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Learning3DViewer(
                        title = "3D Learning Lab • GLB Prototype",
                        objectType = Learning3DObjectType.GLB
                    )
                }
            }
        }
    }
}
