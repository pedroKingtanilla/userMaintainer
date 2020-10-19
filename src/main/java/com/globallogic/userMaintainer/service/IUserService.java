package com.globallogic.userMaintainer.service;

import com.globallogic.userMaintainer.dto.MessageResponseDTO;
import com.globallogic.userMaintainer.dto.UserRequestDTO;
import com.globallogic.userMaintainer.dto.UserResponseDTO;
import com.globallogic.userMaintainer.model.UserModel;

import java.util.List;

public interface IUserService {

    MessageResponseDTO save(UserRequestDTO requestDto);

    MessageResponseDTO saveAll(List<UserRequestDTO> requestDto);

    List<UserResponseDTO> findAll();

    UserResponseDTO findByNameAndEmail(String name, String email);

    boolean findByEmail(String email);

    UserModel findById(int idUser);

    MessageResponseDTO delete(int idUser);

    MessageResponseDTO update(int idUser, UserRequestDTO requestDto);


}
