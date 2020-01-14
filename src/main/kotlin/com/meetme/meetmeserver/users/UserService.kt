package com.meetme.meetmeserver.users

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
@Transactional
class UserService(val userRepository: UserRepository) {

    fun findById(userId: String): Mono<User> = userRepository.findById(userId)

    fun findAll(): Flux<User> {
        return userRepository.findAll()
    }

    fun save(user: User): Mono<User> {
        if (user.userId == null) {
            user.userId = UUID.randomUUID().toString()
            user.markAsNew()
        }
        return userRepository.save(user)
    }
}
