package com.wealthPark.testApplication.ui.carDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wealthPark.testApplication.R
import com.wealthPark.testApplication.databinding.FragmentProductDetailsBinding
import com.wealthPark.testApplication.utils.dialogUtils.CustomDialogCallback
import com.wealthPark.testApplication.utils.dialogUtils.CustomDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(), IProductDetailsNavigator {

    companion object {
        const val DIALOG_TAG = "dialog"
        const val CAR_ID = "id"
    }

    private lateinit var carDetailsBinding: FragmentProductDetailsBinding
    private val viewModel by viewModels<ProductDetailsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(CAR_ID)?.let { viewModel.setCarId(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carDetailsBinding = FragmentProductDetailsBinding.inflate(layoutInflater)
        carDetailsBinding.lifecycleOwner = this
        carDetailsBinding.carDetailsViewModel = viewModel
        return carDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.isLoading.set(true)
        viewModel.fetchCarDetails()
        viewModel.response.observe(viewLifecycleOwner, Observer { response ->
           // viewModel.getCarData(response)
        })
    }

    override fun showSuccessDialog() {
        showDialog(
            requireContext().getString(R.string.msg_success_title),
            "message",
            true
        )
    }

    override fun showFailedDialog() {
        showDialog(
            requireContext().getString(R.string.msg_failed_title),
            "message",
            true
        )
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

                }

                override fun onPositiveClick() {
                    dialog?.dismiss()
                    findNavController().popBackStack()
                }

                override fun onCloseClick() {
                    dialog?.dismiss()
                }
            },
            isOnlyPositive,
        )
        dialog.show(childFragmentManager, DIALOG_TAG)
    }

}