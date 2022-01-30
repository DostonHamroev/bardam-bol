package uz.hamroev.bardambolnew.model

class Video {

    var titleVideo: String? = null
    var videoId: String? = null
    var downloadBtn: String? = null

    constructor(titleVideo: String?, videoId: String?, downloadBtn: String?) {
        this.titleVideo = titleVideo
        this.videoId = videoId
        this.downloadBtn = downloadBtn
    }
}