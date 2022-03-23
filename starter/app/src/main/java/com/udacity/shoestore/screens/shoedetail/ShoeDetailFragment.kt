package com.udacity.shoestore.screens.shoedetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.SharedViewModel

class ShoeDetailFragment : Fragment() {

    lateinit var binding : FragmentShoeDetailBinding
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = HtmlCompat.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val name =  binding.etFieldName.text.toString()
            val desc = binding.etFieldDesc.text.toString()
            val company = binding.etFieldCompany.text.toString()
            val size =  binding.etFieldSize.text.toString()

            if (name.isNullOrEmpty() || desc.isNullOrEmpty() || company.isNullOrEmpty() || size.isNullOrEmpty()){
                showToast(getString(R.string.error_fields_empty))
            } else {
                sharedViewModel.addShow(
                    Shoe(
                        name = binding.etFieldName.text.toString(),
                        size = binding.etFieldSize.text.toString().toInt(),
                        description = binding.etFieldDesc.text.toString(),
                        company = binding.etFieldCompany.text.toString(),
                        image = sharedViewModel.getImage()
                    )
                )

                findNavController().popBackStack()
            }
        }

        binding.btnCancel.setOnClickListener {
            //No change in list
            findNavController().popBackStack()
        }
    }

    private fun showToast(message : String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    private fun navigateToLogin() {
        findNavController().popBackStack(R.id.loginFragment, true)
        findNavController().navigate(R.id.loginFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> navigateToLogin()
        }
        return super.onOptionsItemSelected(item)
    }
}