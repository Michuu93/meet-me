package com.meetme.meetmeserver.users

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserData(
        @Id @GeneratedValue
        var userId: String,
        var userName: String,
        var userDescription: String?,
        var userStatus: String?,
        var lastPosition: UserPosition?
)
