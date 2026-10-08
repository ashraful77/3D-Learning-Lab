# 3D Learning Lab — Product Requirements Document

## Product Overview
Name: 3D Learning Lab
Platform: Android
Primary user: Teacher / Tutor
Purpose: Interactive 3D teaching and classroom presentation
Architecture: Offline-first

## Product Structure
The application has two clearly separated top-level areas:

1. **3D Object Library** — permanent educational 3D content organized by subject and category.
2. **3D Experiment Lab** — technical/prototype experiments used to validate new 3D capabilities before they become library content.

The Experiment Lab must not make prototype/test objects look like finished educational content.

## Problem
Many educational concepts are difficult to explain using only 2D diagrams, especially three-dimensional geometry, organs, atomic structures, physical objects and spatial relationships.

## Goal
Allow a teacher to open an educational 3D object within a few taps and explore it interactively during teaching.

## 3D Object Library
Initial subject structure:
- Mathematics
- Physics
- Chemistry
- Biology
- Astronomy
- Others

Initial library content:
- Mathematics → Geometry
- Astronomy → Solar System
- Biology → Human Heart
- Future Physics/Chemistry content

### Geometry Experience
The supplied Interactive 3D Geometry Studio contains:
- Cube
- Sphere
- Cylinder
- Cone
- Pyramid
- Torus
- Torus Knot
- Capsule
- Auto rotation
- Wireframe mode
- Colour selection
- Reset view
- Geometry information

The useful educational interactions should be preserved when the experience is migrated into the Android application.

### Solar System Experience
The supplied Interactive 3D Solar System contains:
- Sun
- Mercury through Neptune
- Orbital paths
- Planet selection
- Camera focus/tracking
- Pause/resume orbit
- Orbit visibility toggle
- Orbit speed control
- Camera reset
- Starfield background

The current interaction quality is considered a strong reference for the Astronomy library experience.

## 3D Experiment Lab
The Experiment Lab contains temporary or technical demonstrations such as:
- Multi-Part Test
- GLB Selection Test
- Part Highlight Test
- Model Hierarchy Test
- Animation Test
- Future rendering/interaction prototypes

Experiments may be promoted into the Object Library only after validation and educational design.

## MVP Subjects
- Physics
- Chemistry
- Biology
- Mathematics
- Astronomy
- Others

## Core Viewer Requirements
- Model/content loading
- Rotation
- Zoom
- Pan
- Reset
- Auto-rotation where supported
- Fullscreen

Future:
- Part selection
- Labels
- Highlighting
- Cutaway
- Dimensions
- Animation
- Nets
- Educational information

## Main Screens
Home:
- 3D Object Library
- 3D Experiment Lab

Object Library flow:
Home → Subject → Category → Object → Viewer

Experiment flow:
Home → Experiment → Prototype Viewer/Test

Viewer:
- Large 3D area
- Supported controls
- Educational information where applicable
- Clear back navigation

## Educational Interaction
Future flow: tap part → highlight part → show name → show explanation → show facts.

## Offline Requirements
Navigation, built-in models, viewer, educational information and core interactions must work without internet.

## Performance
- Load models/content on demand.
- Avoid loading the whole library into memory.
- Optimize GLB models and textures.
- Support phones and tablets.
- Maintain smooth interaction where possible.

## MVP Exclusions
Do not initially implement student accounts, XP, coins, rewards, leaderboards, quizzes, exams, progress, homework, AI tutor or social/multiplayer features.

## Success Criteria
A teacher can open:
- Mathematics → Geometry → a shape → interact with it.
- Astronomy → Solar System → explore planets and orbit controls.
- Experiment Lab → Multi-Part Test → validate part selection.

## Future Expansion
Online model library, downloadable model packs, Bengali content (bn-IN), Student Mode, guided lessons, quizzes, tests, progress tracking and curriculum mapping.
