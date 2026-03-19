package com.example.foodies.foodList.foodList

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.example.foodies.core.AbstractVisibility
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

class FoodiesIconUi(
    id: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.isAssignableFrom(ImageView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
)