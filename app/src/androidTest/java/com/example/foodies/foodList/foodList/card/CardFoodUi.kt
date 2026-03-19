package com.example.foodies.foodList.foodList.card

import android.view.View
import androidx.cardview.widget.CardView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class CardFoodUi(
    expectedTitle: String,
    expectedPrice: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction = onView(
        allOf(
            withText(expectedTitle),
            withText(expectedPrice),
            ViewMatchers.isAssignableFrom(CardView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )

    fun click() {
        interaction.perform(ViewActions.click())
    }
}