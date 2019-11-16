package com.meetme.meetmeserver.users

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : R2dbcRepository<User, String>
