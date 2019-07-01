package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User
import java.util.*

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return (if (firstName.isNullOrEmpty()) null else firstName) to (if (lastName.isNullOrEmpty()) null else lastName)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            return null
        }
        return "${if (!firstName.isNullOrBlank()) firstName.toUpperCase()[0] else ""}${if (!lastName.isNullOrBlank()) lastName.toUpperCase()[0] else ""}"
    }

    class Builder {

        private var id: String = ""
        private var firstName : String? = null
        private var lastName : String? = null
        private var avatar : String? = null
        private var rating : Int = 0
        private var respect : Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun firstName(firstName: String?): Builder {
            this.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            this.lastName = lastName
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        fun rating(rating: Int): Builder {
            this.rating = rating
            return this
        }

        fun respect(respect: Int): Builder {
            this.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date?): Builder {
            this.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            this.isOnline = isOnline
            return this
        }

        fun build(): User {
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }

    }

}