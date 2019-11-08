package com.example.demoapp.core

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import com.example.demoapp.R
import com.example.demoapp.imagelist.ImageListActivity
import com.example.demoapp.util.DataBindingIdlingResource
import com.example.demoapp.util.DataBindingIdlingResourceRule
import com.example.demoapp.util.EspressoUtil
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageListActivityTest {

    @get:Rule
    var intentsTestRule = ActivityTestRule(ImageListActivity::class.java)

    val databindingIdlingResource = DataBindingIdlingResource(intentsTestRule)

    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        recyclerView = intentsTestRule.activity.findViewById(R.id.activity_image_list_recycler_view)
        IdlingRegistry.getInstance().register(databindingIdlingResource)
    }

    @Test
    fun displayLoadingWhenFetchingImages() {
        EspressoUtil.waitForView(R.id.activity_image_list_progress_bar)

    }

    @Test
    fun checkRecyclerViewHasItemsWhenFetchImagesIsSuccessful() {
        EspressoUtil.waitForView(R.id.activity_image_list_progress_bar)
        EspressoUtil.waitForView(R.id.activity_image_list_recycler_view)
        Thread.sleep(10000)
        val itemCount = recyclerView.adapter?.itemCount ?: -1

        assertTrue(itemCount > 0)

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(databindingIdlingResource)
    }

}