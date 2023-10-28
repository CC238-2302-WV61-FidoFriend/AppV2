package Beans

class Pet {
    val id: String
    val name: String
    val age: Int
    val description: String
    val imgUrl: String
    val sex: String

    constructor(
        id: String,
        name: String,
        age: Int,
        description: String,
        imgUrl: String,
        sex: String
    ) {
        this.id = id
        this.name = name
        this.age = age
        this.description = description
        this.imgUrl = imgUrl
        this.sex = sex
    }

}