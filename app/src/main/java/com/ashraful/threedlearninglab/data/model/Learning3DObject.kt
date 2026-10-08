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

data class Learning3DPart(
    val id: String,
    val name: String,
    val modelNode: String? = null,
    val description: String = "",
    val label: String? = null,
    val selectable: Boolean = true
)

data class Learning3DObject(
    val id: String,
    val name: String,
    val subjectId: String,
    val categoryId: String,
    val type: Learning3DObjectType,
    val description: String = "",
    val modelFile: String? = null,
    val capabilities: Set<String> = emptySet(),
    val parts: List<Learning3DPart> = emptyList()
)
