package com.example.foodies

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodies.FakeFood.firstFood
import com.example.foodies.FakeFood.foodList
import com.example.foodies.FakeFood.searchedFood
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScenarioTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var foodListPage: FoodListPage
    private lateinit var detailPage: DetailPage
    private lateinit var cartPage: CartPage
    private lateinit var searchPage: SearchPage

    @Before
    fun setup() {
        foodListPage = FoodListPage(foods = emptyList<Food>())
        detailPage = DetailPage(food = firstFood)
        cartPage = CartPage(cartFoods = emptyList<Food>())
        searchPage = SearchPage(searchFoods = emptyList<Food>())
    }

    @Test
    fun get_food_at_start() {
        activityScenarioRule.doWithRecreate { foodListPage.assertLoadingState() }
        foodListPage.waitTillError()
        activityScenarioRule.doWithRecreate { foodListPage.assertErrorState() }

        foodListPage.clickRetry()
        activityScenarioRule.doWithRecreate { foodListPage.assertLoadingState() }
        foodListPage.waitForLoadingList()
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodEmptyListState() } // todo think about them

        foodListPage.refreshList()
        activityScenarioRule.doWithRecreate { foodListPage.assertRefreshState() }
        foodListPage.waitForLoadingList()
        foodListPage = FoodListPage(foods = foodList)
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListState() }
    }

    @Test
    fun get_food_addToCart() {
        get_food_at_start()

        foodListPage.clickAddCard(count = 1)
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListCartState() }

        foodListPage.clickDecrementCard()
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListState() }

        foodListPage.clickAddCard(count = 1)
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListCartState() }

        foodListPage.clickCartButton
        cartPage = CartPage(cartFoods = firstFood)
        activityScenarioRule.doWithRecreate { cartPage.assertCartSufficientState() }

        cartPage.clickDecrementCard
        cartPage = CartPage(cartFoods = emptyList<Food>())
        activityScenarioRule.doWithRecreate { cartPage.assertCartEmptyState() }

        cartPage.clickBackButton
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListState() }

        foodListPage.clickAddCard(count = 1)
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListCartState() }

        foodListPage.clickIncrementCard(count = 2)
        activityScenarioRule.doWithRecreate { foodListPage.assertFoodListCartState() }

        foodListPage.clickCartButton
        cartPage = CartPage(cartFoods = foodList)
        activityScenarioRule.doWithRecreate { cartPage.assertCartSufficientState() }

        cartPage.clickIncrementCard(count = 3)
        activityScenarioRule.doWithRecreate { cartPage.assertCartSufficientState() }
    }

    @Test
    fun get_food_search() {
        get_food_at_start()

        foodListPage.clickSearchButton
        searchPage = SearchPage(cartFoods = emptyList<Food>())
        activityScenarioRule.doWithRecreate { searchPage.assertInputEmptyState() }

        searchPage.addInputLetter("Том Ям")
        searchPage = SearchPage(cartFoods = searchedFood("Том Ям"))
        activityScenarioRule.doWithRecreate { searchPage.assertListChangedState() }

        searchPage.clickAddCard(count = 1)
        activityScenarioRule.doWithRecreate { searchPage.assertInputCartState() }

        searchPage.clickCartButton()
        activityScenarioRule.doWithRecreate { cartPage.assertCartSufficientState() }

        cartPage.clickBackButton()
        activityScenarioRule.doWithRecreate { searchPage.assertInputCartState() }
    }

    @Test
    fun check_detail() {
        get_food_at_start()

        foodListPage.clickFoodCard()
        detailPage = DetailPage(food = firstFood)
        activityScenarioRule.doWithRecreate { detailPage.assertDetailState }

        detailPage.clickCartButton()
        activityScenarioRule.doWithRecreate { cartPage.assertCartSufficientState() }

        cartPage.clickBackButton()
        activityScenarioRule.doWithRecreate { detailPage.assertDetailState() }
    }

    private fun ActivityScenarioRule<*>.doWithRecreate(block: () -> Unit) {
        block.invoke()
        this.scenario.recreate()
        block.invoke()
    }
}

data class Food(
    val id: Int,
    val title: String,
    val url: String,
    val description: String,
    val price: Int,
    val measure: String,
    val energyPer: String,
    val proteinsPer: String,
    val fatsPer: String,
    val carbohydratesPer: String
)