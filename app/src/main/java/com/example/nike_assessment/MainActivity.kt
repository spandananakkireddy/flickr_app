package com.example.nike_assessment

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.nike_assessment.adapters.SliderAdapter
import com.example.nike_assessment.databinding.ActivityMainBinding
import com.example.nike_assessment.repo.Repository
import com.example.nike_assessment.viewmodels.ImageViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: ImageViewModel
    private var repository: Repository? = null
    private lateinit var viewPager2: ViewPager2
    private lateinit var keyword: EditText
    private lateinit var searchButton: Button
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewPager2 = findViewById(R.id.viewPagerSlider)
        keyword = findViewById(R.id.et_keyword)
        searchButton = findViewById(R.id.btn_search)
        searchButton.setOnClickListener(this)
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        repository = Repository.getInstance()
    }

    private fun setAdapter(){
        viewPager2.adapter = SliderAdapter(viewModel.getImages().value?.items!!)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
    }

    override fun onClick(view: View?) {
        if (searchButton.toString() == "")
            Snackbar.make(view?.rootView!!, getString(R.string.valid_input), Snackbar.LENGTH_SHORT).show()
        else {
            viewModel.init(keyword.text.toString())
            getImages()
            if (view != null) {
                hideKeyboard(view)
            }
        }
    }

    //Observe the images livedata and set it to the adapter
    private fun getImages() {
        viewModel.getImages().observe(this, { items ->
            if (items != null) {
                setAdapter()
            }
        })
    }

    private fun hideKeyboard(view: View){
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
