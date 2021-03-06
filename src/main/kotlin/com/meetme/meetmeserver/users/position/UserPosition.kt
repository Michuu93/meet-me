package com.meetme.meetmeserver.users.position

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable

data class UserPosition(
        @Id
        var userId: String,
        var latitude: Double,
        var longitude: Double,
        var positionTimestamp: Double
) : Persistable<String> {
    @Transient
    private var isFirstPosition: Boolean = false

    @JsonIgnore
    override fun getId(): String? = userId

    @JsonIgnore
    override fun isNew(): Boolean = isFirstPosition

    /**
     * Mark entity as new for insert instead of update.
     * Workaroud for https://github.com/spring-projects/spring-data-r2dbc/issues/110
     */
    fun markAsNew() {
        isFirstPosition = true
    }
}
