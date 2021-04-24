package com.tanxf.hiltmvvm.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.loadinghelper.LoadingHelper

abstract class BaseActivity : AppCompatActivity(), BaseFragment.Callback,
    LoadingHelper.OnReloadListener {

    private var mLoadingHelper: LoadingHelper? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initLoading()
    }

    private fun initLoading() {
        mLoadingHelper = LoadingHelper(this)
        mLoadingHelper?.setOnReloadListener(this)
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

    open fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showLoading() {
        mLoadingHelper?.showLoadingView()
    }

    fun hideLoading() {
        mLoadingHelper?.showEmptyView()
    }

    fun showErrorView() {
        mLoadingHelper?.showErrorView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoadingHelper = null
    }

}