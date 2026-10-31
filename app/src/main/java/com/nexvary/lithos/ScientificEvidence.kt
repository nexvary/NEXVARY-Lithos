package com.nexvary.lithos

/** Scientific data must retain provenance; an image guess never establishes identity. */
data class ScientificEvidence(
    val sourceName: String,
    val sourceUrl: String?,
    val license: String,
    val retrievedOn: String,
    val evidenceType: EvidenceType
) {
    fun isTraceable(): Boolean = sourceName.isNotBlank() && license.isNotBlank() && retrievedOn.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))
}
enum class EvidenceType { REFERENCE, FIELD_TEST, LAB_REPORT, IMAGE_MODEL }
data class IdentificationCandidate(val name: String, val confidence: Double) {
    init { require(confidence in 0.0..1.0) }
}
object IdentificationPolicy {
    fun canPresentAsConfirmed(evidence: ScientificEvidence?): Boolean =
        evidence?.isTraceable() == true && evidence.evidenceType == EvidenceType.LAB_REPORT
    fun topCandidates(candidates: List<IdentificationCandidate>, limit: Int = 3): List<IdentificationCandidate> =
        candidates.sortedByDescending { it.confidence }.take(limit.coerceAtLeast(0))
}
