# 3D Learning Lab — Content Schema

## Content Hierarchy
Subject → Category → 3D Object → Model + Parts + Labels + Capabilities + Animations + Educational Information

## Subject
Fields:
- id
- name
- description
- icon
- order
- categories

IDs:
physics, chemistry, biology, mathematics, others

## Category
Fields:
- id
- subjectId
- name
- description
- icon
- order
- objects

## 3D Object
Fields:
- id
- name
- subjectId
- categoryId
- description
- model
- thumbnail
- parts
- labels
- capabilities
- animations
- educationalInfo

Stable IDs include cube, sphere, cylinder, cone, heart and atom.

## Domain Model Implementation
The Android domain layer represents each educational 3D object with a stable ID, subject/category IDs, object type, optional model file, description and capabilities. Rendering remains separate from this data model.

## Model Metadata
Fields:
- file
- format
- version
- size
- thumbnail
- source

Example: models/cylinder.glb, format glb, source BUILT_IN.

## Parts
Fields:
- id
- name
- modelNode
- description
- label
- selectable

Example heart parts: right_atrium, right_ventricle, left_atrium, left_ventricle, aorta, pulmonary_artery.

## Labels
Fields:
- id
- text
- targetPartId
- position
- visibleByDefault

## Capabilities
Possible values:
rotate, zoom, pan, labels, selection, dimensions, net, animation, cutaway

## Animations
Fields:
- id
- name
- description
- animationName
- loop
- autoPlay
- controls

## Educational Information
Fields:
- definition
- keyPoints
- parts
- facts
- notes

Future languages:
- en-IN
- bn-IN

## Geometry Dimensions
Optional fields:
- radius
- diameter
- height
- length
- width
- depth

## Nets
Suitable geometry objects may contain a net model, dimensions, animation and explanation.

## Content Source
Built-in or Downloaded. Future sources may include Updated and Custom.

## Versioning
Every object should have a content version to support future updates.

## Future Educational Metadata
educationLevel, ageRange, classLevel, difficulty, curriculum and board.

## Repository Principle
The UI retrieves objects through a repository interface and does not care whether an object is built in, downloaded or updated.
