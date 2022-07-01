package com.example.materialdesign.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private var isNewList = false
    private val adapter = RecyclerFragmentAdapter { position, data -> /*TODO WH */ }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myRecyclerView.adapter = adapter

        val lat = 12
        val lon = 22
        val z = 22

        val location = Pair(lat, lon)
        val location3D = Triple(lat, lon, z)
        val locationAnotherOne = lat to lon
        val location3DAnother = lat to lon to z

        location.second
        location.first
        location3D.third

        val data = arrayListOf(
            Pair(Data(1,"Earth", type = TYPE_EARTH), false),
            Pair(Data(2,"Earth", type = TYPE_EARTH), false),
            Pair(Data(3,"Mars", "", type = TYPE_MARS), false),
            Pair(Data(4,"Earth", type = TYPE_EARTH), false),
            Pair(Data(5,"Earth", type = TYPE_EARTH), false),
            Pair(Data(6,"Earth", type = TYPE_EARTH), false),
            Pair(Data(7,"Mars", null, type = TYPE_MARS), false)
        )
        data.add(0, Pair(Data(0,"Заголовок", type = TYPE_HEADER), false))

        adapter.setData(data)

        ItemTouchHelper(ItemTouchHelperCallbackSettings(adapter)).attachToRecyclerView(binding.myRecyclerView)

        binding.recyclerActivityDiffUtilFAB.setOnClickListener { changeAdapterData() }
    }


    private fun changeAdapterData() {
        adapter.setData(createItemList(isNewList).map { it }.toMutableList())
        isNewList = !isNewList
    }

    private fun createItemList(instanceNumber: Boolean): MutableList<Pair<Data, Boolean>> {
        return when (instanceNumber) {
            false -> mutableListOf(
                Pair(Data(0, "Header", type = TYPE_MARS), false),
                Pair(Data(1, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(2, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(3, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(4, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(5, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(6, "Mars", "", type = TYPE_MARS), false)
            )
            true -> mutableListOf(
                Pair(Data(0, "Header", type = TYPE_MARS), false),
                Pair(Data(1, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(2, "Jupiter", "", type = TYPE_MARS), false),
                Pair(Data(3, "Mars", "", type = TYPE_MARS), false),
                Pair(Data(4, "Neptune", "", type = TYPE_MARS), false),
                Pair(Data(5, "Saturn", "", type = TYPE_MARS), false),
                Pair(Data(6, "Mars", "", type = TYPE_MARS), false)
            )
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerFragment()
    }

}

class ItemTouchHelperCallbackSettings(private val adapterCallback: ItemTouchHelperAdapter) :
    ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        start: RecyclerView.ViewHolder,
        end: RecyclerView.ViewHolder
    ): Boolean {
        adapterCallback.onItemMove(start.adapterPosition, end.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapterCallback.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is RecyclerFragmentAdapter.MarsViewHolder)
                (viewHolder as RecyclerFragmentAdapter.MarsViewHolder).onItemSelected()
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is RecyclerFragmentAdapter.MarsViewHolder)
            (viewHolder as RecyclerFragmentAdapter.MarsViewHolder).onItemClear()
        super.clearView(recyclerView, viewHolder)
    }
}
