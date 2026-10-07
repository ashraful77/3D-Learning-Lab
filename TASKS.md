# 3D Learning Lab — Development Tasks

## Development Strategy
Do not jump directly into the complete application. Build and validate the 3D technology first.

## Phase 0 — Planning — COMPLETED
- [x] Define concept
- [x] Define teacher-first purpose
- [x] Define offline-first approach
- [x] Prepare PRD
- [x] Prepare architecture
- [x] Prepare content schema
- [x] Prepare development roadmap

## Phase 1 — 3D Technology Prototype
- [x] Create Android project
- [x] Configure Kotlin + Jetpack Compose
- [x] Add SceneView
- [x] Verify Filament rendering
- [ ] Create assets/models directory
- [ ] Add first GLB model
- [ ] Load GLB
- [ ] Render model
- [x] Test rotation
- [x] Test pinch zoom
- [x] Test pan
- [x] Implement reset
- [x] Test auto-rotation
- [ ] Test fullscreen
- [ ] Inspect model hierarchy
- [ ] Test part selection
- [ ] Test animation

### Decision Gate
Do not build the rest of the app until GLB loading, rendering, rotation, zoom, pan, reset, selection and animation work reliably.

## Phase 2 — Reusable 3D Viewer
- [x] ModelViewer component
- [ ] Camera controller
- [ ] Gesture controller
- [ ] Lighting/environment controller
- [ ] Model loader abstraction
- [ ] Viewer state
- [ ] Loading state
- [ ] Error state
- [ ] Viewer controls
- [ ] Fullscreen
- [ ] Reset
- [ ] Auto-rotate
- [ ] Phone testing
- [ ] Tablet testing

## Phase 3 — Content System
- [ ] Subject model
- [ ] Category model
- [ ] Object model
- [ ] Model metadata
- [ ] Part metadata
- [ ] Label metadata
- [ ] Capability metadata
- [ ] Animation metadata
- [ ] EducationalInfo metadata
- [ ] Content repository
- [ ] Built-in content source

## Phase 4 — Application UI
- [ ] Home screen
- [ ] Five subjects
- [ ] Subject screen
- [ ] Category screen
- [ ] Object library
- [ ] Thumbnails
- [ ] Descriptions
- [ ] Navigation
- [ ] Connect library to viewer
- [ ] Back navigation
- [ ] Tablet layout

## Phase 5 — Educational Interactions
- [ ] Labels
- [ ] Part selection
- [ ] Highlighting
- [ ] Information panel
- [ ] Animations
- [ ] Animation controls
- [ ] Cutaway support
- [ ] Dimensions
- [ ] Geometry nets
- [ ] Object-specific controls

## Phase 6 — Initial Models
Mathematics:
- [ ] Cube
- [ ] Sphere
- [ ] Cylinder
- [ ] Cone

Biology:
- [ ] Human Heart

Later:
- [ ] Atom
- [ ] Human cell
- [ ] Skeleton
- [ ] Eye
- [ ] Ear
- [ ] Lungs
- [ ] Brain
- [ ] More geometry
- [ ] Physics models
- [ ] Chemistry models

## Phase 7 — Offline-First System
- [ ] Bundle models
- [ ] Bundle metadata
- [ ] Verify no-internet operation
- [ ] Local content repository
- [ ] Downloaded-content abstraction
- [ ] Model package format
- [ ] Future download system

## Phase 8 — Model Expansion
- [ ] Model quality guidelines
- [ ] Polygon limits
- [ ] Texture guidelines
- [ ] Naming conventions
- [ ] Thumbnails
- [ ] Educational metadata
- [ ] Labels
- [ ] Animations

## Phase 9 — Performance
- [ ] Measure load time
- [ ] Optimize GLB
- [ ] Optimize textures
- [ ] Memory testing
- [ ] Low-end device testing
- [ ] Mid-range device testing
- [ ] Tablet testing
- [ ] Rotation smoothness
- [ ] Navigation performance

## Phase 10 — Classroom UX
- [ ] Large controls
- [ ] High-visibility labels
- [ ] Simple navigation
- [ ] Fullscreen viewer
- [ ] Quick reset
- [ ] Quick object switching
- [ ] Auto-rotate
- [ ] Teacher information panel
- [ ] Prevent accidental navigation during presentation

## Phase 11 — Polish
- [ ] App icon
- [ ] Splash screen
- [ ] Empty states
- [ ] Loading animations
- [ ] Error messages
- [ ] Accessibility review
- [ ] Tablet polish
- [ ] UI consistency
- [ ] Final performance pass

## Phase 12 — Release
- [ ] Final testing
- [ ] Crash testing
- [ ] Offline testing
- [ ] Android version testing
- [ ] APK/AAB build
- [ ] Release configuration
- [ ] Store listing
- [ ] Documentation

## Future — Student Mode
Do not implement during MVP.
- [ ] Student profiles
- [ ] Lessons
- [ ] Guided 3D activities
- [ ] Practice
- [ ] Quizzes
- [ ] Tests
- [ ] Progress
- [ ] Achievements
- [ ] Difficulty levels
- [ ] Bengali educational content
- [ ] English educational content
- [ ] Curriculum mapping

## Development Rule
Understand → Plan → Document → Prototype → Validate → Build → Test → Expand

Do not add major features before the architecture and prototype are stable.
