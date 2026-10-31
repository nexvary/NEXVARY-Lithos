# NEXVARY Lithos — 20-step engineering execution plan

Status convention: implemented = source committed; verified = CI/device evidence inspected. Never conflate them.

1. Audit current repository and CI — inspected source; PR-only run lookup yielded no results, not a build verdict.
2. Resolve build toolchain versions — pinned AGP 8.7.3 / Gradle 8.9 / JDK 17, awaiting CI proof.
3. Introduce localization resources — initial strings added, screen-wide migration pending.
4. Implement bidirectional navigation — basic Arabic/English direction toggle committed.
5. Create original launcher artwork — vector gemstone mark committed; full brand asset review pending.
6. Add privacy-preserving manifest — no INTERNET permission.
7. Implement initial offline encyclopedia — six hardcoded samples; needs Room.
8. Add bilingual search — implemented in prototype.
9. Add mineral details — chemical formula and Mohs hardness.
10. Implement evidence-gated valuation policy — pure Kotlin core and tests added.
11. Add unit-test dependency and CI test task — configured, unverified.
12. Add static analysis gate — lint configured, unverified.
13. Add scientific provenance model — pending.
14. Implement Room storage — pending.
15. Add local specimen collection — pending.
16. Add safe guided mineral tests — pending.
17. Research legal image datasets — pending verified licensing.
18. Integrate on-device classifier with abstention — pending.
19. Device tests and performance/accessibility review — pending.
20. Verified signed release workflow and APK — pending successful build and acceptance.

## Gate
A step is not complete just because a file was committed. Scientific data and image models need provenance, rights, held-out validation and documented uncertainty.
