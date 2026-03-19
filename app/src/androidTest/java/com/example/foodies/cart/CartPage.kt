package com.example.foodies.cart

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.foodies.Food
import com.example.foodies.R
import org.hamcrest.Matcher

class CartPage(private val cartFoods: List<Food>) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val backButton = BackButtonUi(
        id = R.id.backButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    private val textEmpty = TextEmptyUi(
        id = R.id.textEmpty,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    private val cardsUi: List<CardFoodUi> by lazy {
        cartFoods.mapIndexed { index, food ->
            CardFoodUi(
                expectedTitle = food.title,
                expectedPrice = "${food.price} ₽",
                containerIdMatcher, classTypeMatcher
            )
        }
    }

    private val cartButton = CartButtonUi(
        id = R.id.cartButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )


    fun assertCartSufficientState() {
        backButton.assertVisible()
        textEmpty.assertNotVisible()
        cardsUi.assertVisible()
        cardsUi.assertIncrementDecrementButton()
        cartButton.assertVisible()
    }

    fun assertCartEmptyState() {
        backButton.assertVisible()
        textEmpty.assertVisible()
        cardsUi.assertNotVisible()
        cartButton.assertNotVisible()
    }

    fun clickBackButton() {
        backButton.click()
    }
}