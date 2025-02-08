package it.unipi.CardsGallery.controller;


import it.unipi.CardsGallery.CommonConstants;
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
@RequestMapping("/admin") //!!! Forse non va messa neanche la barra !!!
public class AdminController {

    @Autowired
    AdminService adminService;

    //Repository necessari

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
    public ResponseEntity<ResponseWrapper<Void>> deletePost(@RequestBody DeletePostDTO dto) {
        try {
            adminService.deletePost(dto);
            return ResponseEntity.ok(new ResponseWrapper<>("post deleted successfully", null));
        } catch (AuthenticationException | NoAdminException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage(), null));
        }
    }
}
