package com.meetme.meetmeserver.users.position

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UsersPositionService(val userPositionRepository: UserPositionRepository) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun findById(userId: String): Mono<UserPosition> {
        log.trace("UsersPositionService.findById [userId=$userId]")
        return userPositionRepository.findById(userId)
    }

    fun save(userPosition: UserPosition): Mono<UserPosition> {
        log.trace("UsersPositionService.save")
        return userPositionRepository.findById(userPosition.userId).doOnNext {
            log.debug("Actual user position: $it")
            if (userPosition.positionTimestamp > it.positionTimestamp) {
                log.info("New user position: $userPosition")
                userPositionRepository.save(userPosition).subscribe()
            } else {
                log.warn("New user position is older than actual")
            }
        }.switchIfEmpty(Mono.defer {
            log.debug("First user position: $userPosition")
            userPosition.markAsNew()
            userPositionRepository.save(userPosition)
        })
    }

    fun findAll(): Flux<UserPosition> {
        log.trace("UsersPositionService.findAll")
        return userPositionRepository.findAll()
    }

    fun findAllActiveAfter(positionTimestamp: Double): Flux<UserPosition> {
        log.trace("UsersPositionService.findAllActiveAfter [positionTimestamp=$positionTimestamp]")
        return userPositionRepository.findAllAfterTimestamp(positionTimestamp)
    }
}
