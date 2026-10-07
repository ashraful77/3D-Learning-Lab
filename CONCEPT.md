# 3D Learning Lab — Concept

3D Learning Lab is an offline-first Android application designed primarily as a teacher's interactive 3D teaching tool. A teacher selects a subject, category and educational 3D object, then rotates, zooms, pans and explores it while explaining the concept.

## Goal
Make difficult concepts easier to explain visually through interactive 3D models.

## Initial Subjects
1. Physics
2. Chemistry
3. Biology
4. Mathematics
5. Others

## Initial Prototype Models
- Cube
- Sphere
- Cylinder
- Cone
- Human Heart

## Main Flow
Open App → Select Subject → Select Category → Select 3D Object → Open 3D Viewer → Explore/Explain

## Core Interactions
- Rotate
- Zoom
- Pan
- Reset
- Auto-rotate
- Fullscreen

Future interactions:
- Labels
- Part selection
- Highlighting
- Information panels
- Animations
- Cutaway views
- Dimensions
- Geometry nets

## Product Direction
The first version is a teacher presentation and explanation tool, not a student self-learning app. Student Mode is future scope.

Core models and educational content should work without internet. Future downloaded content must remain available offline.

## Technology Direction
- Kotlin
- Jetpack Compose
- SceneView
- Google Filament
- GLB/glTF

Keep the 3D engine behind a dedicated viewer3d layer.

## Long-Term Vision
Build a large interactive 3D educational library for Physics, Chemistry, Biology, Mathematics and other subjects, becoming a practical 3D classroom laboratory.
