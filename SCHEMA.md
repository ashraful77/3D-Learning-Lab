# 3D Learning Lab — Content Schema

## Top-Level Content Collections
The application has two content collections:

- **Library** — production educational content.
- **Experiments** — technical/prototype content.

An experiment is not automatically a library object.

## Content Hierarchy — Library
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
physics, chemistry, biology, mathematics, astronomy, others

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
- source
- contentVersion

Stable IDs include cube, sphere, cylinder, cone, heart and solar_system.

## Experiment
Fields:
- id
- name
- description
- experimentType
- source
- capabilities
- status
- testTarget
- relatedObjectId (optional)

Possible experiment types:
- glb_loading
- selection
- hierarchy
- animation
- rendering
- interaction
- prototype

Possible status values:
- active
- validated
- promoted
- archived

## Model Metadata
Fields:
- file
- format
- version
- size
- thumbnail
- source

Example: models/cylinder.glb, format glb, source BUILT_IN.

## Experience Metadata
Some library content may be procedural rather than GLB-based.

Fields may include:
- experienceType
- renderer
- controller
- assetSource
- offlineRequired

Examples:
- geometry_procedural
- solar_system_procedural
- glb_model

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
rotate, zoom, pan, labels, selection, dimensions, net, animation, cutaway, focus, orbit_control, speed_control, wireframe, color_control

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
Production library:
- BUILT_IN
- DOWNLOADED
- UPDATED
- CUSTOM

Experiments:
- PROTOTYPE
- TEST_ASSET
- SOURCE_HTML
- INTERNAL

## Versioning
Every production object should have a content version to support future updates.

## Future Educational Metadata
educationLevel, ageRange, classLevel, difficulty, curriculum and board.

## Repository Principle
The UI retrieves production objects and experiments through repository interfaces. Rendering code must not decide whether content is production or experimental.

## Migration Rule
HTML source experiences can be registered as migration references during development, but the final offline library entry should not depend on a remote CDN unless the architecture explicitly supports bundled local dependencies.
