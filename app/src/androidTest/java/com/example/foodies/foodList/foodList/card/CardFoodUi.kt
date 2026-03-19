package com.example.foodies.foodList.foodList.card

import android.view.View
import androidx.cardview.widget.CardView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foodies.R
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class CardFoodUi(
    expectedTitle: String,
    expectedPrice: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        allOf(
            withText(expectedTitle),
            withText(expectedPrice),
            isAssignableFrom(CardView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {
    private val incrementButtonCard = IncrementButtonCardUi(id = R.id.incrementButtonCard)
    private val decrementButtonCard = DecrementButtonCardUi(id = R.id.decrementButtonCard)

    fun click() {
        interaction.perform(ViewActions.click())
    }

    fun clickDecrementCard() {
        decrementButtonCard.click()
    }

    fun clickIncrementCard() {
        incrementButtonCard.click()
    }

    fun assertIncrementDecrementButton() {
        incrementButtonCard.assertVisible()
        decrementButtonCard.assertVisible()
    }
}