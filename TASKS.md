# 3D Learning Lab — Development Tasks

## Development Strategy
Do not jump directly into the complete application. Build and validate the 3D technology first, then build the production library and keep technical experiments isolated.

## Phase 0 — Planning — COMPLETED
- [x] Define concept
- [x] Define teacher-first purpose
- [x] Define offline-first approach
- [x] Prepare PRD
- [x] Prepare architecture
- [x] Prepare content schema
- [x] Prepare development roadmap
- [x] Define Object Library vs Experiment Lab

## Phase 1 — 3D Technology Prototype
- [x] Create Android project
- [x] Configure Kotlin + Jetpack Compose
- [x] Add SceneView
- [x] Verify Filament rendering
- [x] Create assets/models directory
- [x] Add first GLB model
- [x] Load GLB
- [x] Render model
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
GLB loading, rendering, rotation, zoom, pan and reset have been validated on a real phone.

Still required for the original 3D technology gate:
- [ ] Test part selection
- [ ] Test model animation

Do not declare the complete 3D technology gate finished until these are validated.

## Phase 2 — Reusable 3D Viewer
- [x] ModelViewer component
- [ ] Camera controller
- [ ] Gesture controller
- [ ] Lighting/environment controller
- [ ] Model loader abstraction
- [ ] Viewer state
- [x] Loading state
- [x] Error state
- [x] Viewer controls
- [ ] Fullscreen
- [x] Reset
- [x] Auto-rotate
- [x] Phone testing
- [ ] Tablet testing

## Phase 3 — Content System
- [ ] Subject model
- [ ] Category model
- [x] Object model
- [ ] Model metadata
- [ ] Part metadata
- [ ] Label metadata
- [ ] Capability metadata
- [ ] Animation metadata
- [ ] EducationalInfo metadata
- [ ] Content repository
- [ ] Built-in content source
- [ ] Separate library and experiment collections
- [ ] Experiment model

## Phase 4 — Application UI
### Top-Level Navigation
- [ ] Home screen with 3D Object Library
- [ ] Home screen with 3D Experiment Lab
- [ ] Separate visual identity for production vs experiments
- [ ] Back navigation

### Object Library
- [ ] Library subject screen
- [ ] Category screen
- [ ] Object library
- [ ] Thumbnails
- [ ] Descriptions
- [ ] Connect library to viewer

### Experiment Lab
- [ ] Experiment list
- [ ] Multi-Part Test
- [ ] GLB Selection Test
- [ ] Part Highlight Test
- [ ] Future technical experiments

### Common
- [ ] Navigation
- [ ] Viewer integration
- [ ] Tablet layout

## Phase 5 — Supplied 3D Experience Migration
### Geometry Studio
- [ ] Analyze supplied 3d Text.html
- [ ] Recreate procedural geometry library
- [ ] Preserve shape switching
- [ ] Preserve auto rotation
- [ ] Preserve wireframe mode
- [ ] Preserve colour control
- [ ] Preserve reset view
- [ ] Preserve geometry information
- [ ] Integrate under Mathematics → Geometry

### Solar System
- [ ] Analyze supplied Solar System.html
- [ ] Recreate Sun and planetary system
- [ ] Preserve orbit animation
- [ ] Preserve planet selection
- [ ] Preserve camera focus/tracking
- [ ] Preserve pause/resume
- [ ] Preserve orbit visibility
- [ ] Preserve speed control
- [ ] Preserve reset camera
- [ ] Preserve starfield
- [ ] Integrate under Astronomy → Solar System

### Migration validation
- [ ] Verify all supplied interactions on Android
- [ ] Verify offline operation
- [ ] Verify performance
- [ ] Verify touch controls
- [ ] Verify phone layout
- [ ] Verify tablet layout

## Phase 6 — Educational Interactions
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

## Phase 7 — Initial Models
Mathematics:
- [ ] Cube
- [ ] Sphere
- [ ] Cylinder
- [ ] Cone
- [ ] Pyramid
- [ ] Torus
- [ ] Torus Knot
- [ ] Capsule

Astronomy:
- [ ] Solar System

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

## Phase 8 — Offline-First System
- [ ] Bundle models
- [ ] Bundle metadata
- [ ] Bundle procedural experience dependencies
- [ ] Verify no-internet operation
- [ ] Local content repository
- [ ] Downloaded-content abstraction
- [ ] Model package format
- [ ] Future download system

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
- [ ] Solar System frame-rate testing

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
