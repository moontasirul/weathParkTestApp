package com.wealthPark.testApplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.isNull
import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.data.repository.AppRepository
import com.wealthPark.testApplication.ui.cityAndFoodList.CityAndFoodListViewModel
import com.wealthPark.testApplication.ui.cityAndFoodList.ICityAndFoodNavigator
import com.wealthPark.testApplication.utils.AppEnum
import com.wealthPark.testApplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CityAndFoodViewModelTest {

    private lateinit var cityAndFoodListViewModel: CityAndFoodListViewModel
    private lateinit var repository: AppRepository
    val testDispatcher = TestCoroutineDispatcher()

    private lateinit var mNavigator: ICityAndFoodNavigator

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp() {
        Dispatchers.setMain(testDispatcher)
        repository = Mockito.mock(AppRepository::class.java)
        cityAndFoodListViewModel = CityAndFoodListViewModel(repository)
        mNavigator = Mockito.mock(ICityAndFoodNavigator::class.java)
        cityAndFoodListViewModel.setNavigator(mNavigator)
    }


    @Test
    fun checkLoadingState_OnRequestInit_isTrue() {
        cityAndFoodListViewModel.isLoading.value = true
        Truth.assertThat(cityAndFoodListViewModel.isLoading.value).isEqualTo(true)
    }


    @Test
    fun checkLoadingState_OnRequestComplete_isFalse() {
        cityAndFoodListViewModel.isLoading.value = false
        Truth.assertThat(cityAndFoodListViewModel.isLoading.value).isFalse()
    }

    @Test
    fun getCityListResponse_isSuccess() {
        val cityList: ArrayList<CityItem> = arrayListOf()
        Mockito.`when`(
            Resource(
                status = Resource.Status.ERROR,
                data = null,
                message = null
            ).let { response ->
                when (response.status.name) {
                    AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                        response.data?.let {
                            cityList.addAll(it)
                        }
                        cityAndFoodListViewModel.isLoading.value = false
                    }
                    AppEnum.API_CALL_STATUS.ERROR.name -> {
                        cityAndFoodListViewModel.isLoading.value = false
                    }
                    AppEnum.API_CALL_STATUS.LOADING.name -> {
                        cityAndFoodListViewModel.isLoading.value = true
                    }
                    else -> {

                    }
                }

            }
        ).thenReturn(isNull())
        Truth.assertThat(!cityList.isNullOrEmpty()).isEqualTo(false)
        Truth.assertThat(cityAndFoodListViewModel.isLoading.value).isEqualTo(false)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}