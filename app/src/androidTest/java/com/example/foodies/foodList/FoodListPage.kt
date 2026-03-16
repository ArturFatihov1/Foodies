package com.example.foodies.foodList

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.foodies.Food
import com.example.foodies.R
import org.hamcrest.Matcher

class FoodListPage(foods: List<Food>) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val foodiesIcon = FoodiesIconUi(
        id = R.id.foodiesIcon,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val searchButtonUi = SearchButtonUi(
        id = R.id.searchButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val foodListUi = FoodListUi(
        id = R.id.foodList,
        list = foods,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val cartButton = CartButtonUi(
        id = R.id.cartButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val incorrectText = IncorrectTextUi(
        id = R.id.incorrectText,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val progressBar = ProgressUi(
        id = R.id.progressBar,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )
    private val retryButton = RetryButtonUi(
        id = R.id.retryButton,
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    fun assertLoadingState() {
        incorrectText.assertNotVisible()
        progressBar.assertVisible()
        retryButton.assertNotVisible()
    }

    fun assertErrorState() {
        incorrectText.assertVisible()
        progressBar.assertNotVisible()
        retryButton.assertVisible()
    }

    fun assertFoodListState() {
        foodiesIcon.assertVisible()
        searchButtonUi.assertVisible()
        foodListUi.initialStateCard()
        progressBar.assertNotVisible()
        cartButton.assertNotVisible()
    }

    fun assertRefreshState() {
        foodiesIcon.assertVisible()
        searchButtonUi.assertVisible()
        foodListUi.initialStateCard()
        progressBar.assertVisible()
        cartButton.assertNotVisible()
    }

    fun assertFoodListCartState() {
        foodiesIcon.assertVisible()
        searchButtonUi.assertVisible()
        foodListUi.assertAddCartState()
        progressBar.assertVisible()
        cartButton.assertVisible()
    }

    fun waitTillError() {
        incorrectText.waitTillError()
    }

    fun waitForLoadingList() {
        progressBar.waitTillGone()
    }

    fun clickRetry() {
        retryButton.click()
    }

    fun pullToRefreshList() {
        foodListUi.pullToRefresh()
    }

    fun clickAddCard(count: Int) {
        foodListUi.clickAddCard(count)
    }

    fun clickDecrementCard() {
        foodListUi.clickDecrementCard()
    }

    fun clickIncrementCard(count: Int) {
        foodListUi.clickIncrementCard(count)
    }

    fun clickCartButton() {
        cartButton.click()
    }

    fun clickSearchButton() {
        searchButtonUi.click()
    }

    fun clickFoodCard() {
        foodListUi.clickFoodCard()
    }

}