package com.example.foodies.detail

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ImageFoodUi(
    id: Int,
    url: String,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(ImageView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
)
