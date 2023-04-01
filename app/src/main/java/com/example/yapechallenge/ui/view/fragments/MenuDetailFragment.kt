package com.example.yapechallenge.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.FragmentMenuDetailBinding
import com.example.yapechallenge.domain.model.Menu
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDetailFragment : Fragment() {

    private lateinit var binding: FragmentMenuDetailBinding
    private val args: MenuDetailFragmentArgs by navArgs()
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu = args.menu
        loadDates()

        binding.menuDetailToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.btnMapFragment.setOnClickListener {
            goToMapFragment()
        }
    }

    private fun goToMapFragment() {
        val action = MenuDetailFragmentDirections.actionMenuDetailFragmentToMapFragment(menu)
        findNavController().navigate(action)
    }

    private fun loadDates() {
        Picasso.get().load(menu.img).error(R.drawable.ic_block).into(binding.imgMenu)
        binding.txtName.text = menu.name
        binding.txtDescription.text = menu.description
        binding.txtIngredient.text = menu.ingredient
    }
}