package com.example.demoapp.imagelist

import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.R
import com.example.demoapp.core.BaseActivity
import com.example.demoapp.data.network.Status
import com.example.demoapp.databinding.ActivityImageListBinding
import com.example.demoapp.ui.EndlessScrollListener
import com.example.demoapp.ui.ImageItemUiModel
import com.example.demoapp.ui.ImageListViewState
import com.example.demoapp.util.observe
import org.koin.android.ext.android.inject
import java.io.Serializable

private const val FIRST_PAGE = 1

class ImageListActivity : BaseActivity<ImageListViewModel, ActivityImageListBinding>(ImageListViewModel::class) {

    private val listAdapter: ImageListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.fetchImages(FIRST_PAGE)
        } else {
            viewModel.restoreInstanceSate(savedInstanceState)
        }

    }

    override fun initUserInterface() {
        initRecyclerView()
        binding.activityImageListButtonTryAgain.setOnClickListener {
            viewModel.fetchImages(1)
        }
    }

    override fun attachViewModelObservers() {
        viewModel.imageListViewState.observe(this) {
            binding.viewState = it
            binding.executePendingBindings()
            listAdapter.updateImageList(it.getImages())
        }

    }

    override fun getContentLayoutResourceId(): Int = R.layout.activity_image_list

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)

        binding.activityImageListRecyclerView.apply {
            adapter = listAdapter
            this.layoutManager = layoutManager
            addOnScrollListener(object : EndlessScrollListener(layoutManager) {
                override fun onLoadMore(page: Int) {
                    fetchImages(page)
                }

            })
        }
    }

    private fun fetchImages(page: Int) = viewModel.fetchImages(page)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveInstanceState(outState, listAdapter.getImageList())
    }

}
