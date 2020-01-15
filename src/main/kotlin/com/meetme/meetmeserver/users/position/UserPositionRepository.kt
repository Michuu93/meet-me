package com.meetme.meetmeserver.users.position

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.r2dbc.repository.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


@Repository
interface UserPositionRepository : R2dbcRepository<UserPosition, String> {

    @Query("SELECT * FROM user_position WHERE position_timestamp >= :timestamp")
    fun findAllAfterTimestamp(timestamp: Double): Flux<UserPosition>

}
