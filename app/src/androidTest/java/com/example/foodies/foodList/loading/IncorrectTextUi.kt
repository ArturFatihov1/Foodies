package com.example.foodies.foodList.loading

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.core.AbstractVisibility
import com.example.foodies.core.waitTillDisplayed
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class IncorrectTextUi(
    private val id: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(AppCompatTextView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {
    fun waitTillError() {
        onView(isRoot()).perform(waitTillDisplayed(id, 1500))
    }
}