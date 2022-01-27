package uz.hamroev.bardambolnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realpacific.clickshrinkeffect.applyClickShrink
import render.animations.Attention
import render.animations.Render
import uz.hamroev.bardambolnew.databinding.ItemContentBinding
import uz.hamroev.bardambolnew.model.Content

class ContentAdapter(
    var context: Context,
    var list: ArrayList<Content>,
    var onMyContentClickListener: OnMyContentClickListener
) :
    RecyclerView.Adapter<ContentAdapter.VhContent>() {


    inner class VhContent(var itemContentBinding: ItemContentBinding) :
        RecyclerView.ViewHolder(itemContentBinding.root) {


        fun onBind(content: Content, position: Int) {
            itemContentBinding.titleContent.text = content.titleContent
            content.imageContent?.let { itemContentBinding.imageContent.setImageResource(it) }
            content.mainImage?.let { itemContentBinding.mainContentLinear.setBackgroundResource(it) }


            itemContentBinding.root.setOnClickListener {
                onMyContentClickListener.onContentClick(content, position)
            }

            val render = Render(context)
            render.setDuration(700)
            render.setAnimation(Attention.Bounce(itemContentBinding.root))
            render.start()

//            itemContentBinding.root.applyClickShrink()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhContent {
        return VhContent(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhContent, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyContentClickListener {
        fun onContentClick(content: Content, position: Int)
    }
}