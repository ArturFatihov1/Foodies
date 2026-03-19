package com.example.foodies.foodList.foodList.card

import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers.allOf

class InitialButtonCardUi(id: Int) : AbstractVisibility(
    interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(AppCompatButton::class.java),
        )
    )
) {

    fun click() {
        interaction.perform(ViewActions.click())
    }
}

