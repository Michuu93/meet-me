package com.meetme.meetmeserver.users.position

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@Transactional
class UsersPositionService(val userPositionRepository: UserPositionRepository) {

    fun save(userPosition: UserPosition) {
        userPosition.lastActivity = System.currentTimeMillis()

        userPositionRepository.findById(userPosition.userId).doOnNext {
            if ((userPosition.lastActivity as Long) > it.lastActivity!!.toLong()) {
                userPositionRepository.save(userPosition)
            }
        }.switchIfEmpty(Mono.defer {
            userPosition.markAsNew()
            userPositionRepository.save(userPosition)
        }).subscribe()
    }
}
