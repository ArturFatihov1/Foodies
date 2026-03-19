package com.example.foodies.foodList.foodList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.foodies.Food
import com.example.foodies.R
import com.example.foodies.core.AbstractVisibility
import com.example.foodies.foodList.foodList.card.CardFoodUi
import com.example.foodies.foodList.foodList.card.DecrementButtonCardUi
import com.example.foodies.foodList.foodList.card.IncrementButtonCardUi
import com.example.foodies.foodList.foodList.card.InitialButtonCardUi
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

class FoodListUi(
    val id: Int,
    val list: List<Food>,
    val containerIdMatcher: Matcher<View>,
    val classTypeMatcher: Matcher<View>
) : AbstractVisibility(
    interaction = onView(
        CoreMatchers.allOf(
            withId(id),
            isAssignableFrom(RecyclerView::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
) {
    private val cardsUi: List<CardFoodUi> by lazy {
        list.mapIndexed { index, food ->
            CardFoodUi(
                expectedTitle = food.title,
                expectedPrice = "${food.price} ₽",
                containerIdMatcher, classTypeMatcher
            )
        }
    }

    private val initialButtonCard = InitialButtonCardUi(id = R.id.initialButtonCard)
    private val incrementButtonCard = IncrementButtonCardUi(id = R.id.incrementButtonCard)
    private val decrementButtonCard = DecrementButtonCardUi(id = R.id.decrementButtonCard)


    fun assertInitialStateCard() {
        initialButtonCard.assertVisible()
        incrementButtonCard.assertNotVisible()
        decrementButtonCard.assertNotVisible()
    }

    fun assertAddCartState() {
        initialButtonCard.assertNotVisible()
        incrementButtonCard.assertVisible()
        decrementButtonCard.assertVisible()
    }

    fun pullToRefresh() {
        onView(withId(R.id.your_recycler_view_id)).perform(ViewActions.swipeDown())
    }

    fun clickAddCard() {
        initialButtonCard.click()
    }

    fun clickDecrementCard() {
        decrementButtonCard.click()
    }

    fun clickIncrementCard() {
        incrementButtonCard.click()
    }

    fun clickFoodCard(position: Int) {
        cardsUi[position].click()
    }

}

