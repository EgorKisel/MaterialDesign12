package com.example.materialdesign.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentRecyclerItemEarthBinding
import com.example.materialdesign.databinding.FragmentRecyclerItemHeaderBinding
import com.example.materialdesign.databinding.FragmentRecyclerItemMarsBinding

const val TYPE_EARTH = 0
const val TYPE_MARS = 1
const val TYPE_HEADER = 2

class RecyclerFragmentAdapter(val callback: SomeActionAdapter) :
    RecyclerView.Adapter<RecyclerFragmentAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    private val dataList: MutableList<Pair<Data,Boolean>> = mutableListOf()
    fun setData(newData: MutableList<Pair<Data,Boolean>>) {

        val result = DiffUtil.calculateDiff(DiffCallback(dataList,newData))
        result.dispatchUpdatesTo(this)
        this.dataList.clear()
        this.dataList.addAll( newData)

    }

    private fun generateItem() = Pair(Data(0,"Mars(G)", "", type = TYPE_MARS),false)

    override fun getItemViewType(position: Int): Int {
        return dataList[position].first.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    FragmentRecyclerItemEarthBinding.inflate(
                        LayoutInflater.from(parent.context)
                    )
                EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding =
                    FragmentRecyclerItemMarsBinding.inflate(
                        LayoutInflater.from(parent.context)
                    )
                MarsViewHolder(binding.root)
            }
            TYPE_HEADER -> {
                val binding =
                    FragmentRecyclerItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context)
                    )
                HeaderViewHolder(binding)
            }
            else -> {
                val binding =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isNullOrEmpty()){
            super.onBindViewHolder(holder, position, payloads)
        }else{
            val combine = createCombinedPayload(payloads as List<Change<Pair<Data,Boolean>>>)
            if(combine.oldData.first.name!=combine.newData.first.name)
                FragmentRecyclerItemMarsBinding.bind(holder.itemView).name.text = combine.newData.first.name
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        //val myViewType = getItemViewType(position)
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class EarthViewHolder(private val binding: FragmentRecyclerItemEarthBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>) {
            binding.name.text = data.first.name
        }
    }

    class HeaderViewHolder(private val binding: FragmentRecyclerItemHeaderBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>) {
            binding.name.text = data.first.name
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        override fun bind(data: Pair<Data,Boolean>) {
            val binding = FragmentRecyclerItemMarsBinding.bind(itemView)
            binding.name.text = data.first.name
            binding.marsDescriptionTextView.visibility = if(data.second) View.VISIBLE else View.GONE
            binding.addItemImageView.setOnClickListener {
                //callback.someAction(...., ...)
                dataList.add(adapterPosition,generateItem())
                notifyItemInserted(adapterPosition)
            }
            binding.removeItemImageView.setOnClickListener {
                //callback.someAction(..., ...)
                dataList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
            }

            binding.moveItemUp.setOnClickListener {
                // убрать ошибку java.lang.IndexOutOfBoundsException:
                dataList.removeAt(layoutPosition).apply {
                        dataList.add(layoutPosition -1, this)
                }
                //не работает)
                if (layoutPosition > 1){
                    notifyItemMoved(layoutPosition,layoutPosition-1)
                }
            }
            binding.moveItemDown.setOnClickListener {
                // убрать ошибку java.lang.IndexOutOfBoundsException:
                dataList.removeAt(layoutPosition).apply {
                    dataList.add(layoutPosition +1, this)
                }
                notifyItemMoved(layoutPosition,layoutPosition+1)
            }

            binding.name.setOnClickListener {
                dataList[layoutPosition] = dataList[layoutPosition].let {
                    it.first to !it.second
                }
                //binding.marsDescriptionTextView.visibility = if(dataList[layoutPosition].second) View.VISIBLE else View.GONE
                notifyItemChanged(layoutPosition)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Data,Boolean>)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        dataList.removeAt(fromPosition).apply {
            dataList.add(toPosition , this)
        }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

}