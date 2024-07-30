package caio.desafiosantanderdio.controller;

import caio.desafiosantanderdio.User;
import caio.desafiosantanderdio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserRepository UserRepository;

    @Autowired
    public UserController(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @GetMapping
    public List<User> listarTodos() {
        return UserRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return UserRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User criar(@RequestBody User User) {
        return UserRepository.save(User);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User User) {
        return UserRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(User.getName());
                    existingUser.setEmail(User.getEmail());
                    existingUser.setPassword(User.getPassword());
                    return ResponseEntity.ok(UserRepository.save(existingUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        UserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
