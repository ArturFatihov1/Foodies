package com.example.foodies.foodList.foodList.card

import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers

class DecrementButtonCardUi(id: Int) : AbstractVisibility(
    interaction = Espresso.onView(
        CoreMatchers.allOf(
            withId(id),
            isAssignableFrom(AppCompatButton::class.java),
        )
    )
) {
    fun click() {
        interaction.perform(ViewActions.click())
    }
}