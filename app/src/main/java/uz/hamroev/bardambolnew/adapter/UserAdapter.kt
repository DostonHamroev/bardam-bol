package uz.hamroev.bardambolnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.bardambolnew.databinding.ItemUserBinding
import uz.hamroev.bardambolnew.model.User

class UserAdapter(var context: Context, var list: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.VhUser>() {

    inner class VhUser(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: User, position: Int) {
            itemUserBinding.userName.text = user.user_name
            itemUserBinding.userInfo.text = user.user_info
            user.user_icon?.let { itemUserBinding.userIcon.setImageResource(it) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhUser {
        return VhUser(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhUser, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}