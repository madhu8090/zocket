package com.mad.zocket.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mad.zocket.GlideApp
import com.mad.zocket.activity.base.XActivity
import com.mad.zocket.adapter.CategoryAdapter
import com.mad.zocket.adapter.TaskAdapter
import com.mad.zocket.api.ApiHelper
import com.mad.zocket.api.FBRetrofitBuilder
import com.mad.zocket.base.ViewModelFactory
import com.mad.zocket.databinding.ActivityPageDetailsBinding
import com.mad.zocket.model.Category
import com.mad.zocket.model.Constants
import com.mad.zocket.model.Datum
import com.mad.zocket.model.PageImage
import com.mad.zocket.utils.GlideUtil
import com.mad.zocket.utils.Status
import com.mad.zocket.viewmodel.LoginViewModel

class PageDetailsActivity : XActivity() {
    lateinit var mBinding: ActivityPageDetailsBinding
    lateinit var datum: Datum
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPageDetailsBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        setupViewModel()
        val extras = intent.extras
        if (!extras!!.isEmpty) {
            datum = Gson().fromJson(extras.getString(Constants.PAGE_INFO), Datum::class.java)
            setupObservers(datum.getId().toString())
            title = datum.getName()
            showDetails()
        }
    }

    private fun showDetails() {
        mBinding.pageName.text = datum.getName()
        mBinding.imagePage

        mBinding.recyclerCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mBinding.recyclerTasks.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        showList()
    }

    private fun showList() {
        val categoryAdapter = CategoryAdapter(datum.getCategoryList() as List<Category>)
        mBinding.recyclerCategory.adapter = categoryAdapter
        categoryAdapter.notifyDataSetChanged()

        val taskAdapter = TaskAdapter(datum.getTasks() as List<String>)
        mBinding.recyclerTasks.adapter = taskAdapter
        taskAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(FBRetrofitBuilder.apiService))
        ).get(LoginViewModel::class.java)
    }

    private fun setupObservers(pageId: String) {
        viewModel.getPageImage(pageId).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        mProgressBar!!.hide()
                        resource.data?.let { it -> showImage(it) }
                    }
                    Status.ERROR -> {
                        mProgressBar!!.hide()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun showImage(it: PageImage) {
        GlideUtil().loadImage(this, it.getImageData()!!.getUrl(), mBinding.imagePage)
    }

}
