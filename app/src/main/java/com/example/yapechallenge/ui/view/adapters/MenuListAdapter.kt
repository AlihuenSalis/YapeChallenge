package com.example.yapechallenge.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yapechallenge.R
import com.example.yapechallenge.domain.model.Menu
import com.example.yapechallenge.ui.view.viewHolder.MenuListViewHolder

class MenuListAdapter(
    private var menuList: MutableList<Menu>,
    private val onClickListener: (Menu) -> Unit
) : RecyclerView.Adapter<MenuListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MenuListViewHolder(layoutInflater.inflate(R.layout.rv_menu_list, parent, false))
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        val item = menuList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = menuList.size

    fun updateMenuList(menuListFiltered: MutableList<Menu>) {
        this.menuList = menuListFiltered
        notifyDataSetChanged()
    }
}