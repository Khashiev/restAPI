package com.nazirka.restAPI.controller;

import com.nazirka.restAPI.DTO.CatDTO;
import com.nazirka.restAPI.entity.Cat;
import com.nazirka.restAPI.repository.CatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final CatRepository catRepository;

    @Operation(
            summary = "add new cat to db",
            description = "get cat's DTO and save entity in db"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info("Add cat " + catRepository.save(
                Cat.builder()
                        .name(catDTO.getName())
                        .age(catDTO.getAge())
                        .weight(catDTO.getWeight())
                        .build()));
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepository.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepository.existsById(cat.getId())) {
            return "No such cat";
        }
        return catRepository.save(cat).toString();
    }
}
