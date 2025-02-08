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
            return ResponseEntity.ok(new ResponseWrapper<Void>(CommonConstants.CREATION_OK_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.MUST_BE_LOGGED_MSG, null));
        } catch (NoAdminException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.NO_ADMIN_MSG, null));
        } catch (ExistingEntityException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/card")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteCard(@RequestBody AdminCardNoFieldsDTO adminDelete) {
        try {
            adminService.deleteCard(adminDelete);
            return ResponseEntity.ok(new ResponseWrapper<Void>(CommonConstants.DELETE_OK_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.MUST_BE_LOGGED_MSG, null));
        } catch (NoAdminException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.NO_ADMIN_MSG, null));
        } catch (ExistingEntityException e){
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @PutMapping("/card")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> updateCard(@RequestBody AdminCardDTO adminCardDTO) {
        try {
            adminService.updateCard(adminCardDTO);
            return ResponseEntity.ok(new ResponseWrapper<Void>(CommonConstants.UPDATE_OK_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.MUST_BE_LOGGED_MSG, null));
        } catch (NoAdminException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.NO_ADMIN_MSG, null));
        } catch (ExistingEntityException e){
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/user")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(@RequestBody UserDTO dto) {
        try {
            adminService.deleteUser(dto);
            return ResponseEntity.ok(new ResponseWrapper<Void>(CommonConstants.DELETE_OK_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.MUST_BE_LOGGED_MSG, null));
        } catch (NoAdminException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.NO_ADMIN_MSG, null));
        } catch (ExistingEntityException e){
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(e.getMessage(), null));
        }
    }

    @DeleteMapping("/post")
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Void>> deletePost(@RequestBody DeletePostDTO dto) {
        try {
            adminService.deletePost(dto);
            return ResponseEntity.ok(new ResponseWrapper<>(CommonConstants.DELETE_OK_MSG, null));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(CommonConstants.MUST_BE_LOGGED_MSG, null));
        } catch (NoAdminException e) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<Void>(CommonConstants.NO_ADMIN_MSG, null));
        }
    }
}
