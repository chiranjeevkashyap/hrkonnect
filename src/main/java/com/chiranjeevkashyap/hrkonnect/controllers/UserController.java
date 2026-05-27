package com.chiranjeevkashyap.hrkonnect.controllers;

import com.chiranjeevkashyap.hrkonnect.advices.ResponseBody;
import com.chiranjeevkashyap.hrkonnect.dto.UserDto;
import com.chiranjeevkashyap.hrkonnect.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<UserDto> users = service.getUsers();
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseBody<>(service.getUser(id)));
    }

    @GetMapping("{id}/balances")
    public ResponseEntity<?> getBalances(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBalances(id));
    }

    @GetMapping("{id}/balances/{typeId}")
    public ResponseEntity<?> getBalance(@PathVariable Long id, @PathVariable Long typeId) {
        return ResponseEntity.ok(service.getBalance(id, typeId));
    }
}
