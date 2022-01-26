package uz.hamroev.bardambolnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.bardambolnew.databinding.MainItemBinding
import uz.hamroev.bardambolnew.model.MainMenu

class MenuMainAdapter(
    var context: Context,
    var list: ArrayList<MainMenu>,
    var onMyMainMenuClickLIstener: OnMyMainMenuClickLIstener
) :
    RecyclerView.Adapter<MenuMainAdapter.VhMenuMain>() {


    inner class VhMenuMain(var mainItemBinding: MainItemBinding) :
        RecyclerView.ViewHolder(mainItemBinding.root) {

        fun onBind(mainMenu: MainMenu, position: Int) {
            mainItemBinding.menuName.text = mainMenu.menuName
            mainMenu.menuIcon?.let { mainItemBinding.menuIcon.setImageResource(it) }

            mainItemBinding.root.setOnClickListener {
                onMyMainMenuClickLIstener.onClickMenu(mainMenu, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMenuMain {
        return VhMenuMain(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhMenuMain, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyMainMenuClickLIstener {
        fun onClickMenu(mainMenu: MainMenu, position: Int)
    }
}