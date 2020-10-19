package com.globallogic.userMaintainer.util;

import com.globallogic.userMaintainer.dto.PhoneDTO;
import com.globallogic.userMaintainer.dto.UserRequestDTO;
import com.globallogic.userMaintainer.dto.UserResponseDTO;
import com.globallogic.userMaintainer.model.PhoneModel;
import com.globallogic.userMaintainer.model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static void toEntity(UserRequestDTO userRequestDto, UserModel userModel){
        userModel.setName(userRequestDto.getName());
        userModel.setEmail(userRequestDto.getEmail());
        userModel.setPassword(userRequestDto.getPassword());
    }

    public static void toEntity(PhoneDTO phoneDTO, PhoneModel phoneModel){
        phoneModel.setCityCode(phoneDTO.getCityCode());
        phoneModel.setCountryCode(phoneDTO.getCountryCode());
        phoneModel.setNumberPhone(phoneDTO.getNumber());
        phoneModel.setIdUser(phoneDTO.getIdUser());
    }

    public static UserModel toEntity(UserRequestDTO userRequestDto){
        UserModel userModel = new UserModel();
        toEntity(userRequestDto, userModel);

        return userModel;
    }

    public static List<UserModel> toEntity(List<UserRequestDTO> userRequestDTOList){

        List<UserModel> userModelList = userRequestDTOList.stream().map(DtoMapper::toEntity).collect(Collectors.toList());

        return userModelList;
    }

    public static PhoneModel toEntity(PhoneDTO phoneDTO){

        PhoneModel phoneModel = new PhoneModel();
        toEntity(phoneDTO, phoneModel);

        return phoneModel;
    }

    public static List<PhoneModel> toEntity(List<PhoneDTO> phoneDTOList, int idUser){
        phoneDTOList.stream().forEach(phoneDTO -> {
            phoneDTO.setIdUser(idUser);
        });

        List<PhoneModel> phoneModelList = phoneDTOList.stream().map(DtoMapper::toEntity).collect(Collectors.toList());
        return phoneModelList;
    }

    public static void toDTO(UserResponseDTO userResponseDTO, UserModel userModel){
        userResponseDTO.setName(userModel.getName());
        userResponseDTO.setEmail(userModel.getEmail());
        userResponseDTO.setId(userModel.getId());
        userResponseDTO.setLastLogin(userModel.getLastLogin());
        userResponseDTO.setModified(userModel.getModified());
        userResponseDTO.setIsactive(userModel.isIsactive());
        userResponseDTO.setCreated(userModel.getCreated());
    }

    public static UserResponseDTO toDTO(UserModel userModel){
        UserResponseDTO userResponseDTO = null;
        if (userModel != null){
            userResponseDTO = new UserResponseDTO();
            toDTO(userResponseDTO, userModel);
        }

        return userResponseDTO;
    }

    public static List<UserResponseDTO> toDTO(List<UserModel> userModelList){

        List<UserResponseDTO> userResponseDTOList = userModelList.stream().map(DtoMapper::toDTO).collect(Collectors.toList());

        return userResponseDTOList;
    }

    public static void toPhoneDTO(PhoneDTO phoneDTO, PhoneModel phoneModel){
        phoneDTO.setIdUser(phoneModel.getIdUser());
        phoneDTO.setCityCode(phoneModel.getCityCode());
        phoneDTO.setCountryCode(phoneModel.getCountryCode());
        phoneDTO.setNumber(phoneModel.getNumberPhone());
    }

    public static PhoneDTO toPhoneDTO(PhoneModel phoneModel){
        PhoneDTO phoneDTO = null;
        if (phoneModel != null){
            phoneDTO = new PhoneDTO();
            toPhoneDTO(phoneDTO, phoneModel);
        }
        return phoneDTO;
    }

    public static List<PhoneDTO> toPhoneDTO(List<PhoneModel> phoneModelList){
        return phoneModelList.stream().map(DtoMapper::toPhoneDTO).collect(Collectors.toList());
    }

}
