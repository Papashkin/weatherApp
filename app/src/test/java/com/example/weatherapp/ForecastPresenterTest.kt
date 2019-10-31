package com.example.weatherapp

import com.example.weatherapp.forecast.ForecastPresenter
import com.example.weatherapp.forecast.`ForecastView$$State`
import com.example.weatherapp.forecast.data.ForecastRepository
import com.example.weatherapp.forecast.domain.GetForecasts
import com.example.weatherapp.forecast.models.ForecastsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ForecastPresenterTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var getForecasts: GetForecasts
    @Mock
    lateinit var forecastRepository: ForecastRepository
    @Mock
    lateinit var viewState: `ForecastView$$State`

    lateinit var forecastPresenter: ForecastPresenter

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        forecastPresenter = ForecastPresenter(getForecasts, forecastRepository)
        forecastPresenter.setViewState(viewState)
    }

    @Test
    fun loadDataTest() = testCoroutineRule.runBlockingTest {
            val model = Mockito.mock(ForecastsModel::class.java)
            whenever(getForecasts()).thenReturn(model)
            forecastPresenter.loadData()
            verify(viewState).update(model)
        }
}

@ExperimentalCoroutinesApi
class TestCoroutineRule : TestRule {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope()

    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {

            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(testCoroutineDispatcher)

                base.evaluate()

                Dispatchers.resetMain()
                testCoroutineScope.cleanupTestCoroutines()
            }
        }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest { block() }
}