package com.example.foodies.foodList.foodList

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

class SearchButtonUi(
    id: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.isAssignableFrom(AppCompatButton::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {
    fun click() {
        interaction.perform(ViewActions.click())
    }
}