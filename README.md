# NEXVARY-Lithos

Offline-first Android field guide for identifying gemstones, rocks, minerals, ores, fossils, and possible meteorites.

## Core principles
- Fully local operation; no backend, user accounts, or analytics by default.
- Arabic RTL and English; Kotlin + Jetpack Compose.
- Local Room/SQLite reference encyclopedia and private specimen collection.
- On-device image classification with uncertainty and candidate alternatives; visual identification is never definitive.
- Guided, non-destructive mineral tests and safety warnings.
- Indicative valuation only where sourced, dated reference data and sufficient specimen attributes exist; otherwise show insufficient evidence.
- Optional manual import of cryptographically signed offline reference updates.

## Planned modules
1. Discovery and camera capture
2. Encyclopedia and taxonomy
3. Guided tests and identification
4. Reference valuation
5. Private collection and PDF export

## Quality gates
- Reproducible Android build and CI checks.
- Offline operation verified in airplane mode.
- Model accuracy measured against held-out, documented specimens.
- No uploads of photos, locations, or user data.

Status: Project charter only; application code, datasets, trained model, and verified APK are not yet implemented.
