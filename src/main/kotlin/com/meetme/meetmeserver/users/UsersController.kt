package com.meetme.meetmeserver.users

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/users")
class UsersController(val userService: UserService) {
    @GetMapping
    fun findAll(): Flux<User> = userService.findAll()

    @PostMapping
    fun findAllById(@RequestBody ids: List<String>) =
            userService.findAllById(ids)
}
