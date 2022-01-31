package uz.hamroev.bardambolnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import uz.hamroev.bardambolnew.databinding.ItemVideoBinding
import uz.hamroev.bardambolnew.model.Video

class VideoAdapter(
    var context: Context,
    var list: ArrayList<Video>,
    var onMyVideoClickListener: OnMyVideoClickListener
) :
    RecyclerView.Adapter<VideoAdapter.VhVideo>() {


    inner class VhVideo(var itemVideoBinding: ItemVideoBinding) :
        RecyclerView.ViewHolder(itemVideoBinding.root) {

        fun onBind(video: Video, position: Int) {

            itemVideoBinding.titleVideo.text = video.titleVideo

            itemVideoBinding.youtubePlayer.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    var vidId: String = video.videoId!!
                    youTubePlayer.loadVideo(vidId, 0.0f)
                    youTubePlayer.pause()
                }
            })

            itemVideoBinding.donwloadImage.setOnClickListener {
                onMyVideoClickListener.onDownload(video, position, itemVideoBinding.cardDownloadBtn)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhVideo {
        return VhVideo(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhVideo, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyVideoClickListener {
        fun onDownload(video: Video, position: Int, view: View)
    }
}