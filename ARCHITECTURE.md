# 3D Learning Lab — Architecture

## Goal
Separate UI, business logic, educational content and 3D rendering so the application remains maintainable and expandable.

## Technology
- Kotlin
- Jetpack Compose
- Android
- SceneView
- Google Filament
- GLB/glTF

The exact SceneView version/API must be validated during the prototype.

## Layered Architecture
UI → Presentation/ViewModels → Domain → Data/Repository → 3D Viewer Layer → SceneView/Filament

## Suggested Project Structure
data/local
data/models
data/repository
domain/model
domain/usecase
ui/home
ui/subject
ui/library
ui/viewer
ui/components
navigation
viewer3d
MainActivity.kt

## UI Layer
Jetpack Compose handles screens, controls, labels, information panels, dialogs and loading/error states. UI must not contain model-loading logic.

## Presentation Layer
ViewModels manage selected subject, category, object, viewer settings, label visibility, selected model part and loading/error state.

## Domain Layer
Application-independent models and use cases such as GetSubjects, GetCategories, GetObjects, GetObjectDetails and GetViewerCapabilities.

## Data Layer
A repository abstracts content storage so the UI does not care whether content is built in or downloaded.

## 3D Viewer Layer
Components:
- ModelLoader
- CameraController
- GestureController
- LightingController
- AnimationController
- SelectionController
- ViewerState

## Model Format
Primary format: GLB / glTF.

## Loading Strategy
Load only the selected model. Do not load the whole library at startup.

Object selected → read metadata → load GLB → create model instance → display viewer.

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

## Biggest Technical Risk
The first prototype must prove GLB loading, rendering, rotation, zoom, pan, reset, model hierarchy, part selection and animation.
