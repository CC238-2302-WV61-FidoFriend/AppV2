package Beans

class User {
    val id: Int
    val firstName: String
    val lastName: String
    val email: String
    val dob: String
    val isVet: Boolean
    val address: String
    val description: String
    val imgUrl: String
    val pets: Array<String>

    constructor(
        id: Int,
        firstName: String,
        lastName: String,
        email: String,
        dob: String,
        isVet: Boolean,
        address: String,
        description: String,
        imgUrl: String,
        pets: Array<String>
    ) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.dob = dob
        this.isVet = isVet
        this.address = address
        this.description = description
        this.imgUrl = imgUrl
        this.pets = pets
    }
}