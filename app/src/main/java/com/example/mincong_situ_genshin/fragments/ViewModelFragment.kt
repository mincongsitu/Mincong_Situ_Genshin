package com.example.mincong_situ_genshin.fragments

import androidx.fragment.app.Fragment
import com.example.mincong_situ_genshin.di.DI

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }

}

