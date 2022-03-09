package com.wealthPark.testApplication.utils.dialogUtils

import com.wealthPark.testApplication.ui.base.IBaseNavigator

interface IDialogNavigation : IBaseNavigator {
    fun onNext()
    fun onPositive()
    fun onCancel()
}