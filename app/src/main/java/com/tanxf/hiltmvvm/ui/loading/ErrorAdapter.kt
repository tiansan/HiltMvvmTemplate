/*
 * Copyright (c) 2019. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tanxf.hiltmvvm.ui.loading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dylanc.loadinghelper.LoadingHelper
import com.tanxf.hiltmvvm.R

/**
 * @author Dylan Cai
 */
class ErrorAdapter : LoadingHelper.Adapter<ErrorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.layout_error, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        holder.btnReload.setOnClickListener {
            holder?.onReloadListener?.onReload()
        }
    }

    class ViewHolder internal constructor(rootView: View) : LoadingHelper.ViewHolder(rootView) {
        val btnReload: View = rootView.findViewById(R.id.btn_reload)

    }
}