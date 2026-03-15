package com.example.foodies

internal object FakeFood {

    private val fakeFoodList = listOf(
        Food(
            id = 1,
            title = "Том Ям",
            url = "1.jpg",
            description = "Знаменитый острый тайский суп с морепродуктами  и рыбой на основе лемонграсса, грибов, репчатого лука и свежих томатов",
            price = 480,
            measure = "250",
            energyPer = "307.8",
            proteinsPer = "6.1",
            fatsPer = "11.4",
            carbohydratesPer = "45.1"
        ),
        Food(
            id = 2,
            title = "Авокадо Кранч Маки 8шт",
            url = "1.jpg",
            description = "Ролл с нежным мясом камчатского краба, копченой курицей и авокадо.Украшается соусом\\\"Унаги\\\" и легким майонезом  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            price = 470,
            measure = "250",
            energyPer = "307.8",
            proteinsPer = "6.1",
            fatsPer = "11.4",
            carbohydratesPer = "45.1"
        )
    )

    val firstFood = fakeFoodList.first()
    val foodList = fakeFoodList.take(fakeFoodList.size)

    fun searchedFood(query: String) = fakeFoodList.filter { food ->
        food.title.contains(query, ignoreCase = true)
    }
}