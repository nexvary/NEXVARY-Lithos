package com.nexvary.lithos

import org.junit.Assert.*
import org.junit.Test

class ScientificEvidenceTest {
    @Test fun modelPredictionIsNotProof() {
        val image = ScientificEvidence("Model A", null, "internal", "2026-10-31", EvidenceType.IMAGE_MODEL)
        assertFalse(IdentificationPolicy.canPresentAsConfirmed(image))
    }
    @Test fun laboratoryEvidenceCanBeConfirmed() {
        val lab = ScientificEvidence("Accredited lab", null, "owner-provided", "2026-10-31", EvidenceType.LAB_REPORT)
        assertTrue(IdentificationPolicy.canPresentAsConfirmed(lab))
    }
    @Test fun candidatesAreSorted() {
        val sorted = IdentificationPolicy.topCandidates(listOf(
            IdentificationCandidate("A", 0.2), IdentificationCandidate("B", 0.8)
        ))
        assertEquals("B", sorted.first().name)
    }
}
