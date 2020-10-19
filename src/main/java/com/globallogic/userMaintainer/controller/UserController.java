package com.globallogic.userMaintainer.controller;

import com.globallogic.userMaintainer.dto.MessageResponseDTO;
import com.globallogic.userMaintainer.dto.UserRequestDTO;
import com.globallogic.userMaintainer.dto.UserResponseDTO;
import com.globallogic.userMaintainer.service.IUserService;
import com.globallogic.userMaintainer.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/users")
    public ResponseEntity getUsersDetails(){
        List<UserResponseDTO> userResponseDTOList = iUserService.findAll();
        if (userResponseDTOList == null){
            return ErrorUtil.errorNotFound();
        }
        return ResponseEntity.ok(userResponseDTOList);
    }

    @GetMapping("/user")
    public ResponseEntity getUserDetails(@RequestParam("name") String name, @RequestParam("email") String email){

            UserResponseDTO userResponseDTO = iUserService.findByNameAndEmail(name, email);
            if (userResponseDTO != null)
                return ResponseEntity.ok(userResponseDTO);
            else {
                return ErrorUtil.errorNotFound();
            }
    }

    @PostMapping("/user")
    public @ResponseBody
    ResponseEntity save(@Valid @RequestBody UserRequestDTO requestDto){
        MessageResponseDTO messageResponseDTO = iUserService.save(requestDto);
        if (messageResponseDTO.getErrorDTO() != null){
            return ErrorUtil.errorConflict(messageResponseDTO);
        }
        return ResponseEntity.ok(messageResponseDTO);
    }

    @PostMapping("/users")
    public ResponseEntity saveAll(@Valid @RequestBody List<UserRequestDTO> userRequestDTOList){
        MessageResponseDTO messageResponseDTO = iUserService.saveAll(userRequestDTOList);
        if (messageResponseDTO.getErrorDTO() != null){
            return ErrorUtil.errorConflict(messageResponseDTO);
        }
        return ResponseEntity.ok(messageResponseDTO);
    }

    @PutMapping("/user/{idUser}")
    public ResponseEntity updateUser(@Valid @RequestBody UserRequestDTO requestDto, @PathVariable int idUser){

        logger.debug("updateUser ");
        MessageResponseDTO messageResponseDTO = iUserService.update(idUser, requestDto);

        return ResponseEntity.ok(messageResponseDTO);
    }

    @DeleteMapping("/user/{idUser}")
    public ResponseEntity deleteUser(@PathVariable int idUser){

        logger.debug("deleteUser ");
        MessageResponseDTO messageResponseDTO = iUserService.delete(idUser);
        return ResponseEntity.ok(messageResponseDTO);
    }


}
