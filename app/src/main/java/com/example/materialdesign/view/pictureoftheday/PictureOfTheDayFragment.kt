package com.example.materialdesign.view.pictureoftheday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding?=null
    private val binding: FragmentPictureOfTheDayBinding
        get(){
            return _binding!!
        }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendServerRequest()

        onWikiClick()
        setBottomSheetBehavior()

    }
    private fun setBottomSheetBehavior(){
        val behavior = (binding.lifeHack.bottomSheetContainer.layoutParams as CoordinatorLayout.LayoutParams).behavior as BottomSheetBehavior
        behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

        })
    }

    private fun onWikiClick(){
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun renderData(appState: AppState){
        when(appState){
            is AppState.Error -> {
                //
            }
            is AppState.Loading -> {
               //
            }
            is AppState.Success -> {
                binding.imageView.load(appState.serverResponseData.hdurl){

                }
                binding.lifeHack.title.text = appState.serverResponseData.title
                binding.lifeHack.explanation.text = appState.serverResponseData.explanation
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PictureOfTheDayFragment()
    }
}