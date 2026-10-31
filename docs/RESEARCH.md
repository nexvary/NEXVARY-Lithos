# Research and competitor audit (initial)

## Research questions
- Which offline mineral/rock classification approaches generalize beyond curated photos?
- Which datasets allow commercial redistribution and model training?
- How can specimens be identified by physical tests without damaging them?
- What defensible local valuation references exist for raw versus cut stones?

## Candidates to evaluate (not approved dependencies)
- Mindat: mineral reference; review restrictions on redistribution, scraping and AI training before use.
- RRUFF: mineral spectroscopy reference; verify licensing and permitted data use.
- Open-source Android camera + LiteRT/ONNX Runtime Mobile inference examples: compare binary size, latency and supported hardware.
- Public geological datasets: verify provenance, taxonomy, licenses, coverage and image permissions independently.

## Competitor comparison rubric
Offline functionality; uncertainty reporting; image recognition; scientific reference depth; Arabic/RTL; sample collection; valuation transparency; privacy; cost.

## Decision
Do not import external datasets, pretrained models, images or code until license and quality review. Keep the identification interface independent from a specific ML runtime.
