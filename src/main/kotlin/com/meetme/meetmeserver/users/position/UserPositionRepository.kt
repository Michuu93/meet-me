package com.meetme.meetmeserver.users.position

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface UserPositionRepository : R2dbcRepository<UserPosition, String>
