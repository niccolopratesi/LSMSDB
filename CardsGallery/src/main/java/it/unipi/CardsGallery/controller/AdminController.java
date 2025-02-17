package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.DTO.*;
import it.unipi.CardsGallery.service.AdminService;
import it.unipi.CardsGallery.service.exception.AuthenticationException;
import it.unipi.CardsGallery.service.exception.ExistingEntityException;
import it.unipi.CardsGallery.service.exception.NoAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/card/magic")
    public ResponseEntity<ResponseWrapper<Void>> newMagicCard(@RequestBody MagicCardDTO newMagicCardDTO) {
        try {
            adminService.insertMagicCard(newMagicCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("magic card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PostMapping("/card/pokemon")
    public ResponseEntity<ResponseWrapper<Void>> newPokemonCard(@RequestBody PokemonCardDTO newPokemonCardDTO) {
        try {
            adminService.insertPokemonCard(newPokemonCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("pokemon card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PostMapping("/card/yugioh")
    public ResponseEntity<ResponseWrapper<Void>> newYugiohCard(@RequestBody YugiohCardDTO newYugiohCardDTO) {
        try {
            adminService.insertYugiohCard(newYugiohCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("yugioh card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PostMapping("/card")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> newCard(@RequestBody AdminCardDTO dto) {
        try {
            adminService.insertCard(dto);
            return ResponseEntity.ok(new ResponseWrapper<Void>("card created successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/card")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteCard(@RequestBody AdminCardNoFieldsDTO adminDelete) {
        try {
            adminService.deleteCard(adminDelete);
            return ResponseEntity.ok(new ResponseWrapper<Void>("card deleted successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @PutMapping("/card")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateCard(@RequestBody AdminCardDTO adminCardDTO) {
        try {
            adminService.updateCard(adminCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<Void>("card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @PutMapping("/card/magic")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateMagicCard(@RequestBody MagicCardDTO magicCardDTO) {
        try {
            adminService.updateMagicCard(magicCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("magic card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PutMapping("/card/pokemon")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updatePokemonCard(@RequestBody PokemonCardDTO pokemonCardDTO) {
        try {
            adminService.updatePokemonCard(pokemonCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("pokemon card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @PutMapping("/card/yugioh")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateYugiohCard(@RequestBody YugiohCardDTO yugiohCardDTO) {
        try {
            adminService.updateYugiohCard(yugiohCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<>("yugioh card updated successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/user")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(@RequestBody UserDTO dto) {
        try {
            adminService.deleteUser(dto);
            return ResponseEntity.ok(new ResponseWrapper<Void>("user deleted successfully", null));
        } catch (AuthenticationException | NoAdminException | ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/post")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deletePost(@RequestBody AdminDeletePostDTO dto) {
        try {
            adminService.deletePost(dto);
            return ResponseEntity.ok(new ResponseWrapper<>("post deleted successfully", null));
        } catch (AuthenticationException | NoAdminException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }
}
