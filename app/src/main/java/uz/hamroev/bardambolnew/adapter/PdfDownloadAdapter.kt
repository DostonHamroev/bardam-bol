package uz.hamroev.bardambolnew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdfview.PDFView
import uz.hamroev.bardambolnew.databinding.ItemPdfDownloadBinding
import uz.hamroev.bardambolnew.model.PdfDownload

class PdfDownloadAdapter(
    var context: Context,
    var list: ArrayList<PdfDownload>,
    var onMyPdfDownloadClickListener: OnMyPdfDownloadClickListener
) :
    RecyclerView.Adapter<PdfDownloadAdapter.VhPdfDownloader>() {

    inner class VhPdfDownloader(var itemPdfDownloadBinding: ItemPdfDownloadBinding) :
        RecyclerView.ViewHolder(itemPdfDownloadBinding.root) {

        fun onBind(pdfDownload: PdfDownload, position: Int) {
            itemPdfDownloadBinding.titlePdfName.text = "${pdfDownload.pdfName}.pdf"

            itemPdfDownloadBinding.cardDownload.setOnClickListener {
                onMyPdfDownloadClickListener.onPdfDownloadClick(
                    pdfDownload,
                    position,
                    itemPdfDownloadBinding.downloadBtn,
                    itemPdfDownloadBinding.loadingBtn,
                    itemPdfDownloadBinding.downloadingCardView,
                    itemPdfDownloadBinding.pdfView,
                    itemPdfDownloadBinding.cardDownload
                )

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhPdfDownloader {
        return VhPdfDownloader(
            ItemPdfDownloadBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VhPdfDownloader, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyPdfDownloadClickListener {
        fun onPdfDownloadClick(
            pdfDownload: PdfDownload,
            position: Int,
            downloadView: View,
            loadingView: View,
            downloadingCardView: View,
            pdfView: PDFView,
            cardMain: View
        )
    }
}