package uz.hamroev.bardambolnew.model

class Image {

    var imageName: String = ""
    var imagePath: String = ""

    constructor(imageName: String, imagePath: String) {
        this.imageName = imageName
        this.imagePath = imagePath
    }
}