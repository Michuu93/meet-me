package com.meetme.meetmeserver.users.position

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/position")
class UsersPositionController(val userPositionService: UsersPositionService) {

    @PostMapping
    fun save(@RequestBody userPosition: UserPosition): Mono<Void> {
        userPositionService.save(userPosition)
        return Mono.empty()
    }
}
