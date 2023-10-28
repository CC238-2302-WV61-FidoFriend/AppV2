package Beans

class Service {
    val id: Int
    val name: String
    val description: String
    val imgUrl: String
    constructor(
        id: Int,
        name: String,
        description: String,
        imgUrl: String
    ){
        this.id=id
        this.name=name
        this.description=description
        this.imgUrl=imgUrl
    }
}