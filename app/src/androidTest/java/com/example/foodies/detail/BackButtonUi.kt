package com.example.foodies.detail

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class BackButtonUi(
    id: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(AppCompatButton::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {
    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}
