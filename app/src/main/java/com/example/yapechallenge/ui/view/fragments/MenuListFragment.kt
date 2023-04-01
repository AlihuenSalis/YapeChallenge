package com.example.yapechallenge.ui.view.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.AlertDialogErrorBinding
import com.example.yapechallenge.databinding.FragmentMenuListBinding
import com.example.yapechallenge.domain.model.Menu
import com.example.yapechallenge.ui.view.adapters.MenuListAdapter
import com.example.yapechallenge.ui.view.viewModel.MenuListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuListFragment : Fragment() {

    private lateinit var binding: FragmentMenuListBinding
    private lateinit var adapter: MenuListAdapter
    private lateinit var menuMutableList: MutableList<Menu>
    private lateinit var viewModel: MenuListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MenuListViewModel::class.java]
        binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllMenus()

        binding.etSearch.addTextChangedListener { menuFilter ->
            val menuListFiltered =
                menuMutableList.filter { menuName ->
                    menuName.name.lowercase()
                        .contains(menuFilter.toString().lowercase()) || menuName.ingredient.lowercase()
                        .contains(menuFilter.toString().lowercase())
                }
            adapter.updateMenuList(menuListFiltered.toMutableList())
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
            binding.rvMenuList.isVisible = !it
        }

        viewModel.isResponseOk.observe(viewLifecycleOwner) {
            if (it != null) {
                menuMutableList = it
                binding.rvMenuList.layoutManager = LinearLayoutManager(requireContext())
                adapter = MenuListAdapter(menuMutableList) { menu ->
                    goToMenuDetailFragment(menu)
                }
                binding.rvMenuList.adapter = adapter
            } else {
                showAlertError()
            }
        }
    }

    private fun showAlertError() {
        val builder = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.alert_dialog_error, null)
        val dialogBind = AlertDialogErrorBinding.bind(view)
        builder.setView(view)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        dialogBind.btnConfirm.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun goToMenuDetailFragment(menu: Menu) {
        val action = MenuListFragmentDirections.actionMenuListFragmentToMenuDetailFragment(menu)
        findNavController().navigate(action)
    }
}