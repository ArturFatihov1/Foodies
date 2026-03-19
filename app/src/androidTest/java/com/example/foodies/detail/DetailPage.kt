package com.example.foodies.detail

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.foodies.Food
import com.example.foodies.R
import com.example.foodies.foodList.foodList.CartButtonUi
import org.hamcrest.Matcher

class DetailPage(food: Food) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val backButton = BackButtonUi(
        id = R.id.backButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val imageFood = ImageFoodUi(
        id = R.id.imageFood,
        url = "https:",
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val titleUi = TitleUi(
        id = R.id.title,
        food.title,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val description = DescriptionUi(
        id = R.id.description,
        food.description,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val listEnergy = ListEnergyUi(
        id = R.id.energyList,
        food.energyList,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val cartButton = CartButtonUi(
        id = R.id.cartButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    fun assertDetailState() {
        backButton.assertVisible()
        imageFood.assertVisible()
        titleUi.assertVisible()
        description.assertVisible()
        listEnergy.assertVisible()
        cartButton.assertVisible()
    }

    fun clickCartButton() {
        cartButton.click()
    }

    fun clickBackButton() {
        backButton.click()
    }

}