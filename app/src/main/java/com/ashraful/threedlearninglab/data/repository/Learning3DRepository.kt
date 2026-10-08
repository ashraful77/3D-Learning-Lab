package com.ashraful.threedlearninglab.data.repository

import com.ashraful.threedlearninglab.data.model.Learning3DCapability
import com.ashraful.threedlearninglab.data.model.Learning3DEducationalInfo
import com.ashraful.threedlearninglab.data.model.Learning3DModelMetadata
import com.ashraful.threedlearninglab.data.model.Learning3DObject
import com.ashraful.threedlearninglab.data.model.Learning3DObjectType
import com.ashraful.threedlearninglab.data.model.Learning3DPart

object Learning3DRepository {

    private val objects = listOf(
        Learning3DObject(
            id = "cube",
            name = "Cube",
            subjectId = "mathematics",
            categoryId = "geometry",
            type = Learning3DObjectType.CUBE,
            description = "A solid with six equal square faces.",
            capabilities = setOf(
                Learning3DCapability.ROTATE,
                Learning3DCapability.ZOOM,
                Learning3DCapability.PAN,
                Learning3DCapability.RESET,
                Learning3DCapability.AUTO_ROTATE,
                Learning3DCapability.DIMENSIONS,
                Learning3DCapability.NET_UNFOLD
            ),
            parts = listOf(
                Learning3DPart(
                    id = "faces",
                    name = "Faces",
                    description = "A cube has six square faces."
                ),
                Learning3DPart(
                    id = "edges",
                    name = "Edges",
                    description = "A cube has twelve equal edges."
                ),
                Learning3DPart(
                    id = "vertices",
                    name = "Vertices",
                    description = "A cube has eight vertices."
                )
            ),
            metadata = Learning3DModelMetadata(
                classLevels = listOf("Class 5", "Class 6", "Class 7"),
                tags = listOf("solid", "geometry", "faces", "edges", "vertices"),
                educationalInfo = Learning3DEducationalInfo(
                    definition = "A cube is a three-dimensional solid with six equal square faces.",
                    keyPoints = listOf(
                        "6 faces",
                        "12 edges",
                        "8 vertices",
                        "All edges have equal length"
                    ),
                    teacherTips = listOf(
                        "Rotate the model to count faces, edges and vertices."
                    ),
                    discussionQuestions = listOf(
                        "How many faces can you see at one time?",
                        "How many edges meet at one vertex?"
                    ),
                    formulas = listOf(
                        "Volume = a³",
                        "Total surface area = 6a²"
                    ),
                    realWorldApplications = listOf(
                        "Dice",
                        "Cubic boxes",
                        "Building blocks"
                    )
                )
            )
        ),
        Learning3DObject(
            id = "sphere",
            name = "Sphere",
            subjectId = "mathematics",
            categoryId = "geometry",
            type = Learning3DObjectType.SPHERE,
            description = "A perfectly round three-dimensional solid.",
            capabilities = setOf(
                Learning3DCapability.ROTATE,
                Learning3DCapability.ZOOM,
                Learning3DCapability.PAN,
                Learning3DCapability.RESET,
                Learning3DCapability.AUTO_ROTATE,
                Learning3DCapability.DIMENSIONS
            ),
            metadata = Learning3DModelMetadata(
                classLevels = listOf("Class 6", "Class 7", "Class 8"),
                tags = listOf("solid", "geometry", "circle", "radius"),
                educationalInfo = Learning3DEducationalInfo(
                    definition = "A sphere is a three-dimensional solid in which every point on its surface is the same distance from its centre.",
                    keyPoints = listOf(
                        "Radius is measured from the centre to the surface.",
                        "Diameter is twice the radius."
                    ),
                    formulas = listOf(
                        "Surface area = 4πr²",
                        "Volume = 4/3πr³"
                    ),
                    realWorldApplications = listOf(
                        "Balls",
                        "Spherical tanks",
                        "Globe models"
                    )
                )
            )
        ),
        Learning3DObject(
            id = "cylinder",
            name = "Cylinder",
            subjectId = "mathematics",
            categoryId = "geometry",
            type = Learning3DObjectType.CYLINDER,
            description = "A solid with two parallel circular bases.",
            capabilities = setOf(
                Learning3DCapability.ROTATE,
                Learning3DCapability.ZOOM,
                Learning3DCapability.PAN,
                Learning3DCapability.RESET,
                Learning3DCapability.AUTO_ROTATE,
                Learning3DCapability.DIMENSIONS
            ),
            metadata = Learning3DModelMetadata(
                classLevels = listOf("Class 7", "Class 8", "Class 9"),
                tags = listOf("solid", "geometry", "circle", "height"),
                educationalInfo = Learning3DEducationalInfo(
                    definition = "A cylinder is a three-dimensional solid with two parallel and congruent circular bases.",
                    keyPoints = listOf(
                        "Two circular bases",
                        "Height is the perpendicular distance between the bases"
                    ),
                    formulas = listOf(
                        "Curved surface area = 2πrh",
                        "Total surface area = 2πr(r + h)",
                        "Volume = πr²h"
                    )
                )
            )
        ),
        Learning3DObject(
            id = "cone",
            name = "Cone",
            subjectId = "mathematics",
            categoryId = "geometry",
            type = Learning3DObjectType.CONE,
            description = "A solid with a circular base and one vertex.",
            capabilities = setOf(
                Learning3DCapability.ROTATE,
                Learning3DCapability.ZOOM,
                Learning3DCapability.PAN,
                Learning3DCapability.RESET,
                Learning3DCapability.AUTO_ROTATE,
                Learning3DCapability.DIMENSIONS
            ),
            metadata = Learning3DModelMetadata(
                classLevels = listOf("Class 8", "Class 9", "Class 10"),
                tags = listOf("solid", "geometry", "circle", "vertex"),
                educationalInfo = Learning3DEducationalInfo(
                    definition = "A cone is a three-dimensional solid with a circular base and a single vertex.",
                    keyPoints = listOf(
                        "One circular base",
                        "One vertex",
                        "Height is perpendicular to the base"
                    ),
                    formulas = listOf(
                        "Curved surface area = πrl",
                        "Total surface area = πr(l + r)",
                        "Volume = 1/3πr²h"
                    )
                )
            )
        )
    )

    fun getAll(): List<Learning3DObject> = objects

    fun getById(id: String): Learning3DObject? =
        objects.firstOrNull { it.id == id }

    fun getBySubject(subjectId: String): List<Learning3DObject> =
        objects.filter { it.subjectId == subjectId }

    fun getByCategory(categoryId: String): List<Learning3DObject> =
        objects.filter { it.categoryId == categoryId }

    fun search(query: String): List<Learning3DObject> {
        val normalized = query.trim().lowercase()
        if (normalized.isEmpty()) return objects

        return objects.filter { item ->
            item.name.lowercase().contains(normalized) ||
                item.description.lowercase().contains(normalized) ||
                item.metadata.tags.any { it.lowercase().contains(normalized) }
        }
    }
}
