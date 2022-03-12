package com.wealthPark.testApplication.ui.cityAndFoodList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.databinding.LayoutCityItemBinding
import com.wealthPark.testApplication.ui.base.BaseViewHolder

class CityListRecyclerViewAdapter(

) :
    RecyclerView.Adapter<CityListRecyclerViewAdapter.CityListViewHolder>() {

    lateinit var mListener: CityItemAdapterListener
    private var cityList: ArrayList<CityItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        //  val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_city_item, parent, false)

        val binding: LayoutCityItemBinding =
            LayoutCityItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return CityListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
//        with(holder) {
//            onBind(cityList[position])
//        }
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<CityItem>) {
        cityList.addAll(list)
        notifyDataSetChanged()
    }


    fun clearItems() {
        cityList.clear()
    }

    /**
     *Set Listener
     */
    fun setListener(listener: CityItemAdapterListener) {
        mListener = listener
    }

    /**
     * Adapter Listener
     */
    interface CityItemAdapterListener {
        fun onCityContent(mCityListModel: CityItem)
        fun onRetryClick()
    }

    inner class CityListViewHolder(
        itemViewBinding: LayoutCityItemBinding
    ) : BaseViewHolder(itemViewBinding.root),
        CityItemViewModel.CityItemViewModelListener {
        private val mBinding: LayoutCityItemBinding = itemViewBinding
        private var mCityItemViewModel: CityItemViewModel? = null

        override fun onBind(position: Int) {
            val mCityListModel: CityItem = cityList[position]
            mCityItemViewModel =
                CityItemViewModel(
                    position,
                    mCityListModel,
                    this
                )
            mBinding.cityItemViewModel = mCityItemViewModel
            mBinding.executePendingBindings()
        }


        override fun onContentDetails(mCityListModel: CityItem) {
            mListener.onCityContent(mCityListModel)
        }
    }
}