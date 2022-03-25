package com.udacity.shoestore.screens.shoedetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.SharedViewModel

class ShoeDetailFragment : Fragment() {

    private var binding: FragmentShoeDetailBinding? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding?.data = sharedViewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.isDataSuccessfullySaved.observe(
            viewLifecycleOwner,
            Observer { isDataSavedSuccessfully ->
                if (isDataSavedSuccessfully) {
                    showToast(getString(R.string.successful_fields_saved))
                    findNavController().popBackStack()
                }
            })

        sharedViewModel.isFieldEmpty.observe(
            viewLifecycleOwner,
            Observer { isFieldEmpty ->
                if (isFieldEmpty) {
                    showToast(getString(R.string.error_fields_empty))
                }

            })

        binding?.btnCancel?.setOnClickListener {
            //No change in list
            findNavController().popBackStack()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedViewModel.updateDataSuccessFlag(false)
        binding = null
    }
}