package com.ashraful.threedlearninglab.data.model

enum class Learning3DObjectType {
    CUBE,
    SPHERE,
    CYLINDER,
    CONE,
    HEART,
    GLB
}

data class Learning3DObject(
    val id: String,
    val name: String,
    val subjectId: String,
    val categoryId: String,
    val type: Learning3DObjectType,
    val description: String = "",
    val modelFile: String? = null,
    val capabilities: Set<String> = emptySet()
)
