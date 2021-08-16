package com.kordia.yourfirstcleanmvi.presentation.activity.main.name

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import com.kordia.yourfirstcleanmvi.presentation.databinding.FragmentNameBinding
import com.kordia.yourfirstcleanmvi.presentation.utils.appstructure.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * NameFragment class.
 *
 * @author Mohammedsaif Kordia
 */
@AndroidEntryPoint
class NameFragment : BaseFragment<FragmentNameBinding>(), View.OnClickListener {

    private val nameVM: NameViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNameBinding
        get() = FragmentNameBinding::inflate

    override fun setup() = with(binding) {
        listener = this@NameFragment
        initObservers()
        nameVM.onTriggerEvent(NameIntent.GetAll)
    }

    private fun initObservers() {
        nameVM.namesLiveData.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Empty -> {
                    Toast.makeText(requireContext(), "No names to show!", Toast.LENGTH_SHORT).show()
                    binding.tvNames.text = ""
                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {

                }
                is DataState.Success -> {
                    val namesList = dataState.data.reversed()
                    val builder = StringBuilder()
                    namesList.forEach {
                        builder.append("${it.name}\n")
                    }
                    binding.tvNames.text = builder.toString()
                }
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btInsert -> {
                if (binding.edName.text.toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Name can't be empty!", Toast.LENGTH_SHORT).show()
                } else {
                    nameVM.onTriggerEvent(NameIntent.InsertName(binding.edName.text.toString()))
                }
            }
            binding.btDeleteAll -> {
                nameVM.onTriggerEvent(NameIntent.DeleteAll)
            }
        }
    }
}