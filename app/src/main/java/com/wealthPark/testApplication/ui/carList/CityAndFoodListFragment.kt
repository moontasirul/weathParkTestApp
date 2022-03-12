package com.wealthPark.testApplication.ui.carList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wealthPark.testApplication.R
import com.wealthPark.testApplication.databinding.FragmentCityAndFoodListBinding
import com.wealthPark.testApplication.ui.details.DetailsFragment
import com.wealthPark.testApplication.utils.dialogUtils.CustomDialogCallback
import com.wealthPark.testApplication.utils.dialogUtils.CustomDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityAndFoodListFragment : Fragment(),
    ICityAndFoodNavigator {

    companion object {

        const val CAR_ID = "id"
    }

    private lateinit var carBinding: FragmentCityAndFoodListBinding
    private val viewModel: CityAndFoodListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carBinding = FragmentCityAndFoodListBinding.inflate(layoutInflater)
        return carBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
        setupObservers()
    }





    private fun setupObservers() {
        viewModel.isLoading.set(true)

        viewModel.cityResponse.observe(requireActivity()) { response ->
            viewModel.getCarResponse(response)
        }
        viewModel.foodResponse.observe(requireActivity()) { response ->
            viewModel.getFoodResponse(response)
        }
    }






    private fun onClickedCar(carId: Int) {
        /*findNavController().navigate(
            R.id.action_carsFragment_to_carDetailFragment,
            bundleOf(CAR_ID to carId)
        )*/
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

}




