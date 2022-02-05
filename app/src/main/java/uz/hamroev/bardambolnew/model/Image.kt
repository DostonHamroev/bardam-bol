package uz.hamroev.bardambolnew.model

class Image {

    var image_Url: String = ""
    var imageName: String = ""
    var imagePath: String = ""

    constructor(image_Url: String, imageName: String, imagePath: String) {
        this.image_Url = image_Url
        this.imageName = imageName
        this.imagePath = imagePath
    }

}