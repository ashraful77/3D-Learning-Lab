package com.ashraful.threedlearninglab.data.model

enum class Learning3DObjectType {
    CUBE,
    SPHERE,
    CYLINDER,
    CONE,
    HEART,
    GLB,
    MULTIPART_GLB
}

enum class Learning3DCapability {
    ROTATE,
    ZOOM,
    PAN,
    RESET,
    AUTO_ROTATE,
    SELECTION,
    LABELS,
    DIMENSIONS,
    ANIMATION,
    CUTAWAY,
    NET_UNFOLD
}

data class Learning3DPart(
    val id: String,
    val name: String,
    val modelNode: String? = null,
    val description: String = "",
    val label: String? = null,
    val selectable: Boolean = true
)

data class Learning3DEducationalInfo(
    val definition: String = "",
    val keyPoints: List<String> = emptyList(),
    val teacherTips: List<String> = emptyList(),
    val discussionQuestions: List<String> = emptyList(),
    val formulas: List<String> = emptyList(),
    val realWorldApplications: List<String> = emptyList()
)

data class Learning3DModelMetadata(
    val classLevels: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val educationalInfo: Learning3DEducationalInfo = Learning3DEducationalInfo()
)

data class Learning3DObject(
    val id: String,
    val name: String,
    val subjectId: String,
    val categoryId: String,
    val type: Learning3DObjectType,
    val description: String = "",
    val modelFile: String? = null,
    val capabilities: Set<Learning3DCapability> = emptySet(),
    val parts: List<Learning3DPart> = emptyList(),
    val metadata: Learning3DModelMetadata = Learning3DModelMetadata()
)
