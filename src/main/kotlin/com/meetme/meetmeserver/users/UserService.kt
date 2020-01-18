package com.meetme.meetmeserver.users

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
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

    fun save(user: User): Mono<User> {
        log.trace("UserService.save [user=$user]")
        if (user.userId == null) {
            user.userId = UUID.randomUUID().toString()
            user.markAsNew()
        }
        return userRepository.save(user)
    }

    fun delete(id: String): Mono<Void> {
        log.trace("UserService.delete [id=$id]")
        return userRepository.deleteById(id)
    }
}
