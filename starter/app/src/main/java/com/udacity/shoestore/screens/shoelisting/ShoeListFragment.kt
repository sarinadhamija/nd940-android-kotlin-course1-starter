package com.udacity.shoestore.screens.shoelisting

import android.os.Bundle
import android.text.Html
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.databinding.LayoutShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.SharedViewModel

class ShoeListFragment : Fragment() {

    lateinit var binding: FragmentShoeListBinding

    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddShoeDetail.setOnClickListener { findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment) }

        sharedViewModel.shoeListLiveData.observe(viewLifecycleOwner, Observer {
            it?.apply {
                this.forEach{
                    val itemBinding: LayoutShoeListItemBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.layout_shoe_list_item,
                        binding.llListParent,
                        false
                    )
                    itemBinding.shoe = it
                    itemBinding.icItem.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            it.image
                        )
                    )
                    binding.llListParent.addView(itemBinding.root)
                }
            }
        })

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