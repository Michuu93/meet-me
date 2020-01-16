package com.meetme.meetmeserver.users

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
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

    @PostMapping
    fun save(@RequestPart user: User,
             @RequestPart("photo", required = false) photo: MultipartFile?): Mono<User> {
        photo?.let {
            user.photo = it.bytes
        }
        return userService.save(user)
    }

//    @PostMapping
//    fun save(@RequestPart user: User): Mono<User> = userService.save(user)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Mono<Void> = userService.delete(id)
}
