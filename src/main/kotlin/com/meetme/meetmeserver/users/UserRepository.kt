package com.meetme.meetmeserver.users

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.r2dbc.repository.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : R2dbcRepository<User, String> {

    @Query("SELECT count(1)>0 FROM user WHERE user_name = :userName")
    fun existsByUserName(userName: String): Mono<Boolean>

}