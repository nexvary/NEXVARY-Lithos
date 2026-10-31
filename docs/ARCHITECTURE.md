# Architecture decision record — 001

## Constraints
Android-first, offline operation, no server, no user accounts, no network permission, Arabic RTL and English localization, premium black UI with bespoke colorful assets.

## Modules (planned)
- app: Compose navigation and presentation; keep domain logic out of UI.
- catalog: local Room schema with provenance, licenses, revisions and taxonomy.
- identification: replaceable inference adapter, local model, top-k candidates and abstention threshold.
- field-tests: non-destructive guided measurements with hazard flags.
- valuation: dated source-backed ranges with uncertainty; abstain on inadequate evidence.
- collection: local-only specimens, export and user-controlled backup.
- updates: optional offline signed bundles with schema/version compatibility checks.

## Immediate engineering risks
1. No bundled Gradle wrapper yet: CI uses Gradle setup action; local developers need installed Gradle until wrapper is generated.
2. Compose/AGP dependency resolution and Android SDK versions require a successful CI build before claiming APK readiness.
3. Arabic RTL is not yet explicitly configured; add locale resources, layout direction, and tests.
4. Current screens are placeholders, not scientific functionality.
5. Image recognition needs legally reusable labeled data, held-out specimen validation, confusion analysis, and an unknown/abstain class.
6. Price data must carry source, timestamp, market, unit, cut/raw status and treatment; never hallucinate prices.
7. Fossils, suspected meteorites, radioactive/toxic ores require safety and legal handling guidance.
8. Any imported third-party content requires explicit license and provenance review.

## Acceptance gates
- CI assembleDebug passes; APK artifact downloadable.
- Airplane mode smoke test, no INTERNET permission, no telemetry SDK.
- Arabic/English and TalkBack navigation tests.
- Dataset and model licenses reviewed before inclusion.
- Calibrated identification confidence with explicit unknown result.
