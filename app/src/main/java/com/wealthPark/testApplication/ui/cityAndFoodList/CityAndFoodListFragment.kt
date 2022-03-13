package com.wealthPark.testApplication.ui.cityAndFoodList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wealthPark.testApplication.R
import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.data.local.model.FoodItem
import com.wealthPark.testApplication.databinding.FragmentCityAndFoodListBinding
import com.wealthPark.testApplication.ui.details.DetailsFragment
import com.wealthPark.testApplication.utils.CustomSwipeRefreshLayout
import com.wealthPark.testApplication.utils.dialogUtils.CustomDialogCallback
import com.wealthPark.testApplication.utils.dialogUtils.CustomDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityAndFoodListFragment : Fragment(),
    ICityAndFoodNavigator, CityListRecyclerViewAdapter.CityItemAdapterListener,
    FoodListRecyclerViewAdapter.FoodItemAdapterListener, CustomSwipeRefreshLayout.Event {

    companion object {
        const val CITY_ITEM = "CITY_ITEM"
        const val FOOD_ITEM = "FOOD_ITEM"
    }

    private lateinit var cityAndFoodBinding: FragmentCityAndFoodListBinding
    private val viewModel by viewModels<CityAndFoodListViewModel>()


    private lateinit var cityAdapter: CityListRecyclerViewAdapter

    private lateinit var foodAdapter: FoodListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cityAndFoodBinding = FragmentCityAndFoodListBinding.inflate(layoutInflater)
        cityAndFoodBinding.lifecycleOwner = this
        cityAndFoodBinding.listViewModel = viewModel
        cityAndFoodBinding.fragmentSwipeContainer.setEventListener(this)
        return cityAndFoodBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
//        cityAndFoodBinding.cityItemTv.visibility = View.GONE
//        cityAndFoodBinding.foodItemTv.visibility = View.GONE
        viewModel.isLoading.value = true
        setupObservers()
    }


    private fun setupObservers() {
        viewModel.cityResponse.observe(requireActivity()) { response ->
            viewModel.getCityResponse(response)
        }
        viewModel.foodResponse.observe(requireActivity()) { response ->
            viewModel.getFoodResponse(response)
        }
    }


    fun showDialog(title: String, message: String, isOnlyPositive: Boolean) {
        var dialog: DialogFragment? = null
        var bntText = requireContext().getString(R.string.yes_txt)
        if (isOnlyPositive) {
            bntText = requireContext().getString(R.string.btn_text_ok)
        }
        dialog = CustomDialogFragment(
            title,
            message,
            bntText,
            requireContext().getString(R.string.cancel_text),
            object : CustomDialogCallback {
                override fun onNextClick() {
                    dialog?.dismiss()
                }

                override fun onPositiveClick() {
                    dialog?.dismiss()
                }

                override fun onCloseClick() {
                    dialog?.dismiss()
                }
            },
            isOnlyPositive,
        )
        dialog.show(childFragmentManager, DetailsFragment.DIALOG_TAG)
    }


    override fun onSetCityInfo(cityList: ArrayList<CityItem>) {
        viewModel.isDataFetching.set(true)
        cityAdapter = CityListRecyclerViewAdapter()
        cityAndFoodBinding.cityRecyclerView.adapter = cityAdapter
        cityAdapter.setListener(this)
        cityAdapter.clearItems()
        cityAdapter.addItems(cityList)
    }

    override fun onSetFoodInfo(foodList: ArrayList<FoodItem>) {
        viewModel.isDataFetching.set(true)
        foodAdapter = FoodListRecyclerViewAdapter()
        cityAndFoodBinding.foodRecyclerView.adapter = foodAdapter
        foodAdapter.setListener(this)
        foodAdapter.clearItems()
        foodAdapter.addItems(foodList)
    }

    override fun onCityContent(mCityListModel: CityItem) {
        val bundle = bundleOf(CITY_ITEM to mCityListModel)
        findNavController().navigate(
            R.id.action_cityAndFoodListFragment_to_DetailFragment,
            bundle
        )
    }

    override fun onRetryClick() {

    }

    override fun onFoodContent(mFoodListModel: FoodItem) {
        findNavController().navigate(
            R.id.action_cityAndFoodListFragment_to_DetailFragment,
            bundleOf(FOOD_ITEM to mFoodListModel)
        )
    }

    override fun onRefresh(view: CustomSwipeRefreshLayout) {
        setupObservers()
    }
}




