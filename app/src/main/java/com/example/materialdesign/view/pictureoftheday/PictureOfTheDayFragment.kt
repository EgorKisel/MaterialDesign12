package com.example.materialdesign.view.pictureoftheday

import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.materialdesign.DAY_BEFORE_YESTERDAY
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign.view.TankFragment
import com.example.materialdesign.view.layouts.CollapsingToolbarFragment
import com.example.materialdesign.view.recycler.RecyclerFragment
import com.example.materialdesign.view.settings.SettingsFragment
import com.example.materialdesign.viewmodel.AppState
import com.example.materialdesign.viewmodel.PictureOfTheDayViewModel
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import java.text.SimpleDateFormat
import java.util.*


class PictureOfTheDayFragment : Fragment() {

    var flag = false

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() {
            return _binding!!
        }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_settings -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingsFragment.newInstance())
                .addToBackStack("")
                .commit()
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded)// проверяем, не умер ли фрагент
                show()
        }, 500)

        //(requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendServerRequest(takeDate(0))

        onWikiClick()
        onChipClick()
        onBottomNavigationViewClick()
        //setBottomSheetBehavior()
//        binding.fab.setOnClickListener {
//            isMain = !isMain
//            if (!isMain){
//                binding.bottomAppBar.navigationIcon = null
//                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
//                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_fab))
//                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other)
//            } else {
//                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_hamburger_menu_bottom_bar)
//                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
//                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_plus_fab))
//                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
//            }
//        }
    }

    private fun show() {
        GuideView.Builder(requireContext())
            .setTitle("Picture of the day")
            .setContentText("By clicking on this button you will see a photo of the day of NASA")
            .setTargetView(binding.chip1)
            .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
            .build()
            .show()
    }

    private fun onBottomNavigationViewClick() {
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.recyclerView -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, RecyclerFragment.newInstance(), null)
                        .addToBackStack("").commit()
                }
                R.id.action_bottom_view_mars -> {
                    binding.imageView.load(R.drawable.bg_mars)
                }
                R.id.action_bottom_view_system -> {
                    binding.imageView.load(R.drawable.bg_system)
                }
                R.id.action_bottom_collapsingToolbarLayout -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, CollapsingToolbarFragment.newInstance(), null)
                        .addToBackStack("").commit()
                }
                R.id.tank -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, TankFragment.newInstance(), null)
                        .addToBackStack("").commit()
                }
            }
            true
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onChipClick() {
        binding.chip1.setOnClickListener {
            viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
                renderData(it)
            }
            viewModel.sendServerRequest()
        }
        binding.chip2.setOnClickListener {
            viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
                renderData(it)
            }
            viewModel.sendServerRequest(takeDate(-1))
        }
        binding.chip3.setOnClickListener {
            viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
                renderData(it)
            }
            viewModel.sendServerRequest(DAY_BEFORE_YESTERDAY)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }

//    private fun setBottomSheetBehavior(){
//        val behavior = (binding.lifeHack.bottomSheetContainer.layoutParams as CoordinatorLayout.LayoutParams).behavior as BottomSheetBehavior
//        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
//        behavior.addBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback(){
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
//                    binding.fab.animate().scaleX(0f).scaleY(0f).setDuration(300).start();
//                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
//                    binding.fab.animate().scaleX(1f).scaleY(1f).setDuration(300).start();
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//
//            }
//
//        })
//    }

    private fun onWikiClick() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.imageView.load(R.drawable.cat)
            }
            is AppState.Loading -> {
                binding.imageView.load(R.drawable.loading)
            }
            is AppState.Success -> {
                binding.imageView.load(appState.serverResponseData.hdurl) {
                    placeholder(R.drawable.loading)
                }
//                binding.lifeHack.title.text = appState.serverResponseData.title
//                binding.lifeHack.explanation.text = appState.serverResponseData.explanation
                if (appState.serverResponseData.mediaType == "video"){
                    showAVideoUrl(appState.serverResponseData.hdurl)
                }
                binding.imageView.setOnClickListener {
                    flag = !flag
                    val cb = ChangeBounds()
                    val cit = ChangeImageTransform()
                    val transitionSet = TransitionSet()
                    transitionSet.addTransition(cb)
                    transitionSet.addTransition(cit)

                    TransitionManager.beginDelayedTransition(binding.root, transitionSet)
                    if (flag){
                        binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    }else{
                        binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    }
                }
            }
        }
    }

    private fun showAVideoUrl(videoUrl: String) = with(binding) {
        with(binding){
            imageView.visibility = View.GONE
            videoOfTheDay.visibility = View.VISIBLE
            videoOfTheDay.text = "Сегодня у нас без картинки дня, но есть видео дня! " +
                    "${videoUrl.toString()} \n кликни >ЗДЕСЬ< чтобы открыть в новом окне"
            videoOfTheDay.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(videoUrl)
                }
                startActivity(i)
            }
        } }

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