package com.wealthPark.testApplication.ui.details

import com.wealthPark.testApplication.ui.base.IBaseNavigator

interface IDetailsNavigator : IBaseNavigator {
    fun showSuccessDialog()
    fun showFailedDialog()
}