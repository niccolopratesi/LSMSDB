package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.service.AdminService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/card/magic")
    public ResponseEntity<ResponseWrapper<Void>> newMagicCard(@Valid @RequestBody MagicCardDTO newMagicCardDTO) {
        try {
            adminService.insertMagicCard(newMagicCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("magic card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PostMapping("/card/pokemon")
    public ResponseEntity<ResponseWrapper<Void>> newPokemonCard(@Valid @RequestBody PokemonCardDTO newPokemonCardDTO) {
        try {
            adminService.insertPokemonCard(newPokemonCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("pokemon card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PostMapping("/card/yugioh")
    public ResponseEntity<ResponseWrapper<Void>> newYugiohCard(@Valid @RequestBody YugiohCardDTO newYugiohCardDTO) {
        try {
            adminService.insertYugiohCard(newYugiohCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("yugioh card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/card")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteCard(@Valid @RequestBody AdminCardNoFieldsDTO adminDelete) {
        try {
            adminService.deleteCard(adminDelete);
            return ResponseEntity.ok(new ResponseWrapper<Void>("card deleted successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @PutMapping("/card/magic")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateMagicCard(@Valid @RequestBody MagicCardDTO magicCardDTO) {
        try {
            adminService.updateMagicCard(magicCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("magic card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PutMapping("/card/pokemon")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updatePokemonCard(@Valid @RequestBody PokemonCardDTO pokemonCardDTO) {
        try {
            adminService.updatePokemonCard(pokemonCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("pokemon card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PutMapping("/card/yugioh")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateYugiohCard(@Valid @RequestBody YugiohCardDTO yugiohCardDTO) {
        try {
            adminService.updateYugiohCard(yugiohCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("yugioh card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/user")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(@Valid @RequestBody UserDTO dto) {
        try {
            adminService.deleteUser(dto);
            return ResponseEntity.ok(new ResponseWrapper<Void>("user deleted successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/post")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deletePost(@Valid @RequestBody AdminDeletePostDTO dto) {
        try {
            adminService.deletePost(dto);
            return ResponseEntity.ok(new ResponseWrapper<>("post deleted successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }
}
