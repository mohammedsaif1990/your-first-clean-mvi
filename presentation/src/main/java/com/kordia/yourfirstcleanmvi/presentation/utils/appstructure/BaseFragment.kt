package com.kordia.yourfirstcleanmvi.presentation.utils.appstructure

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.kordia.yourfirstcleanmvi.presentation.activity.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * BaseFragment class the parent of all fragments.
 *
 * @author Mohammedsaif Kordia
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected var hostActivity: Activity? = null
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = _binding as VB

    /**
     * abstract function to pre setup the fragment and the data binding.
     */
    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    /**
     * Do an action on UI thread.
     */
    fun doOnUI(func: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch { func() }
    }

    /**
     * Do an action on IO thread.
     */
    fun doOnIO(func: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch { func() }
    }

    /**
     * Show snack bar.
     */
    fun makeText(view: View, text: String, listener: View.OnClickListener? = null) {
        Snackbar
            .make(view, text, Snackbar.LENGTH_LONG)
            .setAction("Action", listener)
            .show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hostActivity = if (context is MainActivity) context
        else context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        hostActivity = null
    }
}
