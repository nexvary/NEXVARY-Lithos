package com.nexvary.lithos

/** No estimate is emitted without attributable, dated, compatible price evidence. */
object IndicativeValuation {
    data class Estimate(val minimum: Double, val maximum: Double, val source: String, val date: String)
    fun isValid(min: Double, max: Double, source: String): Boolean =
        min.isFinite() && max.isFinite() && min > 0 && max >= min && source.isNotBlank()
    fun estimate(specimenId: String?, evidence: Estimate?): Estimate? {
        if (specimenId.isNullOrBlank() || evidence == null || evidence.date.isBlank()) return null
        return evidence.takeIf { isValid(it.minimum, it.maximum, it.source) }
    }
}
