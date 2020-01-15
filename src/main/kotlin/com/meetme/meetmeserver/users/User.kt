package com.meetme.meetmeserver.users

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable

data class User(
        @Id
        var userId: String?,
        var userName: String,
        var userDescription: String?,
        var userStatus: String?,
        var gender: String?,
        var photo: ByteArray?
) : Persistable<String> {
    @Transient
    private var isNewUser: Boolean = false

    @JsonIgnore
    override fun getId(): String? = userId

    @JsonIgnore
    override fun isNew(): Boolean = isNewUser

    /**
     * Mark entity as new for insert instead of update.
     * Workaroud for https://github.com/spring-projects/spring-data-r2dbc/issues/110
     */
    fun markAsNew() {
        isNewUser = true
    }
}
