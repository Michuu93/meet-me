package com.meetme.meetmeserver.users

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {

    fun findById(userId: String): Mono<User> = userRepository.findById(userId)

    fun findAll(): Flux<User> = userRepository.findAll()


    fun findAllById(ids: List<String>): Flux<User> =
            userRepository.findAllById(ids)

    fun save(user: User): Mono<User> {
        if (user.userId == null) {
            user.userId = UUID.randomUUID().toString()
            user.markAsNew()
        }
        return userRepository.save(user)
    }

    fun delete(id: String): Mono<Void> = userRepository.deleteById(id)
}
