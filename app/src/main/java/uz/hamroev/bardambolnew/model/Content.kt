package uz.hamroev.bardambolnew.model

class Content {

    var titleContent: String? = null
    var imageContent: Int? = null
    var mainImage: Int? = null



    constructor(titleContent: String?, imageContent: Int?, mainImage: Int?) {
        this.titleContent = titleContent
        this.imageContent = imageContent
        this.mainImage = mainImage
    }
}