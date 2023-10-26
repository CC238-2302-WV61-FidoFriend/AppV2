package Beans

class PetRegisterRequest {
    var name: String
    var age: Int
    var description: String
    var imgUrl: String
    var sex: String
    var ownerId: Int

    constructor(
        name: String,
        age: Int,
        description: String,
        imgUrl: String,
        sex: String,
        ownerId: Int
    ) {
        this.name = name
        this.age = age
        this.description = description
        this.imgUrl = imgUrl
        this.sex = sex
        this.ownerId = ownerId
    }
}