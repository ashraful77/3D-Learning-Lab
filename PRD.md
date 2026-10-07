# 3D Learning Lab — Product Requirements Document

## Product Overview
Name: 3D Learning Lab
Platform: Android
Primary user: Teacher / Tutor
Purpose: Interactive 3D teaching and classroom presentation
Architecture: Offline-first

## Problem
Many educational concepts are difficult to explain using only 2D diagrams, especially three-dimensional geometry, organs, atomic structures, physical objects and spatial relationships.

## Goal
Allow a teacher to open an educational 3D object within a few taps and explore it interactively during teaching.

## MVP Subjects
- Physics
- Chemistry
- Biology
- Mathematics
- Others

## Initial Models
Mathematics: Cube, Sphere, Cylinder, Cone
Biology: Human Heart

## Core Viewer Requirements
- Model loading
- Rotation
- Zoom
- Pan
- Reset
- Auto-rotation
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
Home: five subjects.
Category: categories within each subject.
Object Library: object name, thumbnail, description and supported capabilities.
Viewer: large model area with supported controls such as Reset, Auto Rotate, Labels, Information and Fullscreen.

## Educational Interaction
Future flow: tap part → highlight part → show name → show explanation → show facts.

## Offline Requirements
Navigation, built-in models, viewer, educational information and core interactions must work without internet.

## Performance
- Load models on demand.
- Avoid loading the whole library into memory.
- Optimize GLB models and textures.
- Support phones and tablets.
- Maintain smooth interaction where possible.

## MVP Exclusions
Do not initially implement student accounts, XP, coins, rewards, leaderboards, quizzes, exams, progress, homework, AI tutor or social/multiplayer features.

## Success Criteria
A teacher can open Mathematics → Cylinder → rotate → zoom → pan → reset → explain.

The same basic flow must work for Biology → Human Heart.

## Future Expansion
Online model library, downloadable model packs, Bengali content (bn-IN), Student Mode, guided lessons, quizzes, tests, progress tracking and curriculum mapping.
