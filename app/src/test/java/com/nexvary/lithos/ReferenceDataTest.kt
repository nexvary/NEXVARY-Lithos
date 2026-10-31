package com.nexvary.lithos

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class ReferenceDataTest {
    @Test fun noPriceWithoutEvidence() {
        assertEquals(null, IndicativeValuation.estimate(null, null))
    }
    @Test fun validPriceNeedsSourceAndPositiveBounds() {
        assertFalse(IndicativeValuation.isValid(0.0, 20.0, "source"))
        assertFalse(IndicativeValuation.isValid(10.0, 20.0, ""))
    }
}
