package com.flipperdevices.bsb.timer.setup.utils

import androidx.compose.foundation.pager.PagerSnapDistance

object NoLimitPagerSnapDistance : PagerSnapDistance {
    override fun calculateTargetPage(
        startPage: Int,
        suggestedTargetPage: Int,
        velocity: Float,
        pageSize: Int,
        pageSpacing: Int
    ): Int {
        return suggestedTargetPage
    }
}