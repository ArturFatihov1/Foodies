package com.example.foodies.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.EnergyInfo
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ListEnergyUi(
    id: Int,
    energyList: EnergyInfo,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(RecyclerView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
)



