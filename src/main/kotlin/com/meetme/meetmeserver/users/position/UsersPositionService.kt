package com.meetme.meetmeserver.users.position

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Transactional
class UsersPositionService(val userPositionRepository: UserPositionRepository) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun findById(userId: String): Mono<UserPosition> = userPositionRepository.findById(userId)

    fun save(userPosition: UserPosition) =
            userPositionRepository.findById(userPosition.userId).doOnNext {
                log.debug("Actual user position: $it")
                if (userPosition.positionTimestamp.toDouble() > it.positionTimestamp.toDouble()) {
                    log.debug("New user position: $userPosition")
                    userPositionRepository.save(userPosition).subscribe()
                } else {
                    log.debug("New user position is older than actual")
                }
            }.switchIfEmpty(Mono.defer {
                log.debug("First user position: $userPosition")
                userPosition.markAsNew()
                userPositionRepository.save(userPosition)
            })

    fun findAll(): Flux<UserPosition> = userPositionRepository.findAll()
}
