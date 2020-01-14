package com.meetme.meetmeserver.users.position

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@RestController
@RequestMapping("/position")
class UsersPositionController(val userPositionService: UsersPositionService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String): Mono<UserPosition> =
            userPositionService.findById(id)
                    .switchIfEmpty(
                            Mono.error(
                                    ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            "Position for userId=$id not found")))

    @GetMapping
    fun findAll(): Flux<UserPosition> =
            userPositionService.findAll()

    @PostMapping
    fun save(@RequestBody userPosition: UserPosition): Mono<Void> {
        userPositionService.save(userPosition)
                .subscribeOn(Schedulers.elastic())
                .subscribe() // fire and forget
        return Mono.empty()
    }
}
