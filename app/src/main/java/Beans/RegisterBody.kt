package Beans

class RegisterBody {
    var firstName: String
    var lastName: String
    var email: String
    var password: String
    var dob: String
    var isVet: Boolean

    constructor(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        dob: String,
        isVet: Boolean
    ) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.dob = dob
        this.isVet = isVet
    }
}