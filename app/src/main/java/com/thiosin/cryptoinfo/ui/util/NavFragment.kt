package com.thiosin.cryptoinfo.ui.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.base.RainbowCakeViewModel

abstract class NavFragment<VS : Any, VM : RainbowCakeViewModel<VS>, B : ViewBinding> :
        RainbowCakeFragment<VS, VM>() {

    private var _binding: B? = null
    protected val binding get() = _binding ?: throw IllegalStateException("Binding is not set")

    protected val navigator: NavController
        get() = findNavController()

    protected abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}