package com.globallogic.userMaintainer.util;

import com.globallogic.userMaintainer.dto.ErrorDTO;
import com.globallogic.userMaintainer.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ErrorUtil {

    private static ErrorDTO errorDTO;

    @Autowired
    public ErrorUtil(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public static ResponseEntity errorNotFound(){
        errorDTO.setMensaje("resource not found");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity errorBadRequest(){
        errorDTO.setMensaje("Bad Request");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity errorConflict(MessageResponseDTO messageResponseDTO){
        return new ResponseEntity<>(messageResponseDTO.getErrorDTO(), messageResponseDTO.getStatusCode());
    }
}
