package com.example.foodies.detail

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class TitleUi(
    id: Int,
    title: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        allOf(
            withId(id),
            withText(title),
            isAssignableFrom(AppCompatTextView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
)

