package com.meetme.meetmeserver.users

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun findById(userId: String): Mono<User> {
        log.trace("UserService.findById [userId=$userId]")
        return userRepository.findById(userId)
    }

    fun findAll(): Flux<User> {
        log.trace("UserService.findAll")
        return userRepository.findAll()
    }

    fun findAllById(ids: List<String>): Flux<User> {
        log.trace("UserService.findAllById [ids=$ids]")
        return userRepository.findAllById(ids)
    }

    @Transactional
    fun save(user: User): Mono<User> {
        log.trace("UserService.save [user=$user]")
        return userRepository.existsByUserName(user.userName).flatMap {
            var userMono = Mono.empty<User>()
            if (it == true) {
                log.warn("User name ${user.userName} exist!")
            } else {
                if (user.userId == null) {
                    user.userId = UUID.randomUUID().toString()
                    user.markAsNew()
                }
                log.debug("Saving $user")
                userMono = userRepository.save(user)
            }
            userMono
        }
    }

    fun delete(id: String): Mono<Void> {
        log.trace("UserService.delete [id=$id]")
        return userRepository.deleteById(id)
    }
}
