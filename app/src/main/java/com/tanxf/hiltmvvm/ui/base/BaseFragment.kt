package com.tanxf.hiltmvvm.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.dylanc.loadinghelper.LoadingHelper

abstract class BaseFragment : Fragment(), LoadingHelper.OnReloadListener {

    private var mLoadingHelper: LoadingHelper? = null
    private lateinit var mActivity: BaseActivity
    private lateinit var mRootView: View

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.mActivity = context as BaseActivity
            mActivity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        return initLoading()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyLoad()
    }

    abstract fun lazyLoad()

    private fun initLoading(): View {
        mLoadingHelper = LoadingHelper(mRootView)
        mLoadingHelper?.setOnReloadListener(this)
        return mLoadingHelper?.decorView!!
    }

    fun getBaseActivity(): BaseActivity = mActivity

    fun hideKeyBoard() {
        mActivity.hideKeyboard()
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

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }

}