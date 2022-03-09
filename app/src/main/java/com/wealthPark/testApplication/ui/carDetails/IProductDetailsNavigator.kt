package com.wealthPark.testApplication.ui.carDetails

import com.wealthPark.testApplication.ui.base.IBaseNavigator

interface IProductDetailsNavigator : IBaseNavigator {
    fun showSuccessDialog()
    fun showFailedDialog()
}