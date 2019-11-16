package com.meetme.meetmeserver.users

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String): Mono<User> = userService.findById(id)

    @GetMapping
    fun findAll(): Flux<User> = userService.findAll()

    @PostMapping
    fun save(@RequestBody user: User): Mono<User> = userService.save(user)
}
