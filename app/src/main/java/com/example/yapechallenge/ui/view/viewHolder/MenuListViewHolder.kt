package com.example.yapechallenge.ui.view.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.RvMenuListBinding
import com.example.yapechallenge.domain.model.Menu
import com.squareup.picasso.Picasso

class MenuListViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = RvMenuListBinding.bind(view)

    fun render(menu: Menu, onClickListener: (Menu) -> Unit) {
        binding.txtMenuName.text = menu.name
        Picasso.get().load(menu.img)
            .error(R.drawable.ic_block)
            .into(binding.imgMenu)

        binding.txtIngredient.text = menu.ingredient
        binding.itemRecycler.setOnClickListener {
            onClickListener(menu)
        }
    }
}