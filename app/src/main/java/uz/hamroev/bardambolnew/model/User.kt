package uz.hamroev.bardambolnew.model

class User {

    var user_name: String? = null
    var user_info: String? = null
    var user_icon: Int? = null

    constructor(user_name: String?, user_info: String?, user_icon: Int?) {
        this.user_name = user_name
        this.user_info = user_info
        this.user_icon = user_icon
    }
}