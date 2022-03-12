package com.wealthPark.testApplication.ui.cityAndFoodList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wealthPark.testApplication.data.local.model.FoodItem
import com.wealthPark.testApplication.databinding.LayoutFoodItemBinding
import com.wealthPark.testApplication.ui.base.BaseViewHolder

class FoodListRecyclerViewAdapter() :
    RecyclerView.Adapter<FoodListRecyclerViewAdapter.FoodListViewHolder>() {

    lateinit var mListener: FoodItemAdapterListener
    private var foodList: ArrayList<FoodItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val binding: LayoutFoodItemBinding =
            LayoutFoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return FoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<FoodItem>) {
        foodList.addAll(list)
        notifyDataSetChanged()
    }


    fun clearItems() {
        foodList.clear()
    }


    /**
     *Set Listener
     */
    fun setListener(listener: FoodItemAdapterListener) {
        mListener = listener
    }

    /**
     * Adapter Listener
     */
    interface FoodItemAdapterListener {
        fun onFoodContent(mFoodListModel: FoodItem)
        fun onRetryClick()
    }

    inner class FoodListViewHolder(
        itemViewBinding: LayoutFoodItemBinding
    ) : BaseViewHolder(itemViewBinding.root),
        FoodItemViewModel.FoodItemAdapterListener {
        private val mBinding: LayoutFoodItemBinding = itemViewBinding
        private var mFoodItemViewModel: FoodItemViewModel? = null

        //  lateinit var mFoodListModel : FoodItem
        override fun onBind(position: Int) {
            val mFoodListModel = foodList[position]
            mFoodItemViewModel = FoodItemViewModel(
                position,
                mFoodListModel,
                this
            )
            mBinding.foodItemViewModel = mFoodItemViewModel
            mBinding.executePendingBindings()
        }


        override fun onContentDetails(mFoodListModel: FoodItem) {
            mListener.onFoodContent(mFoodListModel)
        }
    }
}