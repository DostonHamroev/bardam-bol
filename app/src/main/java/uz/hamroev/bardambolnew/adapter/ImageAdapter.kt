package uz.hamroev.bardambolnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.chrisbanes.photoview.PhotoView
import uz.hamroev.bardambolnew.databinding.ItemImageBinding
import uz.hamroev.bardambolnew.model.Image

class ImageAdapter(
    var context: Context,
    var list: ArrayList<Image>,
    var onMyImageDownloadClickListener: OnMyImageDownloadClickListener
) :
    RecyclerView.Adapter<ImageAdapter.VhImage>() {



    inner class VhImage(var itemImageBinding: ItemImageBinding) :
        RecyclerView.ViewHolder(itemImageBinding.root) {

        fun OnBind(image: Image, position: Int) {
            itemImageBinding.imageName.text = image.imageName
            itemImageBinding.downloadImage.setOnClickListener {
                onMyImageDownloadClickListener.onDownload(
                    image,
                    position,
                    itemImageBinding.downloadImage,
                    itemImageBinding.progress,
                    itemImageBinding.cardDownloadPunkt,
                    itemImageBinding.imageZoom
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhImage {
        return VhImage(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhImage, position: Int) {
        return holder.OnBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyImageDownloadClickListener {
        fun onDownload(
            image: Image,
            position: Int,
            downloadImage: View,
            progress: View,
            cardDownloadPunkt: View,
            imageZoom: PhotoView
        )

    }
}