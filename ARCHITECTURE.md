# 3D Learning Lab — Architecture

## Goal
Separate UI, business logic, educational content, 3D rendering and experimental prototypes so the application remains maintainable and expandable.

## Technology
- Kotlin
- Jetpack Compose
- Android
- SceneView
- Google Filament
- GLB/glTF
- Three.js/WebGL source experiences may be used as references for migration, but finished library content should use the application's supported rendering architecture where practical.

## Top-Level Application Areas
The application has two top-level destinations:

### 1. Object Library
Production educational content.

Flow:
Home → Object Library → Subject → Category → Object → Viewer

Examples:
- Mathematics → Geometry → Cube
- Mathematics → Geometry → Sphere
- Astronomy → Solar System

### 2. Experiment Lab
Technical prototypes and 3D capability validation.

Flow:
Home → Experiment Lab → Experiment → Prototype Viewer

Examples:
- Multi-Part Test
- Part Selection
- Model Hierarchy
- Animation

Experiments are isolated from production content and can be promoted after validation.

## Layered Architecture
UI → Presentation/ViewModels → Domain → Data/Repository → 3D Viewer Layer → SceneView/Filament

## Suggested Project Structure
data/local
data/models
data/repository
domain/model
domain/usecase
ui/home
ui/library
ui/subject
ui/experiment
ui/viewer
ui/components
navigation
viewer3d

## UI Layer
Jetpack Compose handles:
- Home sections
- Subject/category/object screens
- Experiment list
- Viewer controls
- Labels
- Information panels
- Dialogs
- Loading/error states

UI must not contain model-loading logic.

## Presentation Layer
ViewModels manage:
- Selected top-level area
- Selected subject
- Selected category
- Selected object/experiment
- Viewer settings
- Label visibility
- Selected model part
- Loading/error state

## Domain Layer
Application-independent models and use cases such as:
- GetLibrarySubjects
- GetCategories
- GetObjects
- GetObjectDetails
- GetExperiments
- GetExperimentDetails
- GetViewerCapabilities

## Data Layer
A repository abstracts content storage so the UI does not care whether content is built in or downloaded.

Production library content and experimental content should have separate repository/content collections even when they share the same underlying storage mechanism.

## 3D Viewer Layer
Components:
- ModelLoader
- CameraController
- GestureController
- LightingController
- AnimationController
- SelectionController
- ViewerState

Specialized experiences may have additional controllers, such as:
- SolarSystemController
- GeometryController

## Rendering Strategy
### Production library
Use the reusable Android 3D viewer/rendering architecture.

### Supplied HTML experiences
The Solar System and Geometry HTML files are Three.js/WebGL implementations. They are source references for interaction and visual behavior. Before migration, inspect their rendering logic, controls, object generation and dependencies. Do not blindly embed external CDN content into the offline-first production library.

The first migration targets are:
1. Solar System
2. Geometry Studio

## Loading Strategy
Load only the selected model/experience. Do not load the whole library at startup.

## Future Download Architecture
A downloadable package may contain manifest.json, model.glb, thumbnail.png, labels.json and information.json. Downloaded content remains usable offline.

## Performance
Optimize polygon counts and textures, load on demand, avoid unnecessary recomposition, release resources appropriately, and test low-end phones, mid-range phones and tablets.

## Student Mode
Future Teacher Mode and Student Mode can share the 3D engine, models, metadata, labels, animations, viewer and content repository.

## Architectural Rules
1. Do not hard-code model-specific screens.
2. Do not tightly couple UI to SceneView.
3. Do not require internet for built-in content.
4. Do not load the entire library at startup.
5. Keep metadata separate from rendering code.
6. Keep future online content compatible with offline architecture.
7. Keep Student Mode out of MVP complexity.
8. Keep experimental prototypes separate from production library content.
9. Promote an experiment to the library only after technical validation and educational metadata are complete.

## Biggest Technical Risk
The 3D technology gate must validate:
- GLB loading
- Rendering
- Rotation
- Zoom
- Pan
- Reset
- Model hierarchy
- Part selection
- Animation

The supplied HTML experiences introduce a second migration risk: preserving their useful interactions while maintaining the application's offline-first architecture.
