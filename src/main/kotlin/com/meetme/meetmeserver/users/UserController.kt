package com.meetme.meetmeserver.users

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String): Mono<User> = userService.findById(id).switchIfEmpty(
            Mono.error(
                    ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "User with userId=$id not found")))

    @GetMapping
    fun findAll(): Flux<User> = userService.findAll()

    @PostMapping
    fun save(@RequestBody user: User): Mono<User> = userService.save(user)
}
