package com.ashraful.threedlearninglab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ashraful.threedlearninglab.ui.LearningLabApp
import com.ashraful.threedlearninglab.ui.theme.ThreeDLearningLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThreeDLearningLabTheme {
                LearningLabApp()
            }
        }
    }
}
