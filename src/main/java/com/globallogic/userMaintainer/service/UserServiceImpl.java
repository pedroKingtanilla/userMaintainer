package com.globallogic.userMaintainer.service;

import com.globallogic.userMaintainer.dto.*;
import com.globallogic.userMaintainer.model.UserModel;
import com.globallogic.userMaintainer.repository.IUserRepo;
import com.globallogic.userMaintainer.util.DateUtil;
import com.globallogic.userMaintainer.util.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepo iUserRepo;
    @Autowired
    private IPhoneService iPhoneService;
    @Autowired
    private MessageResponseDTO messageResponseDTO;
    @Autowired
    private ErrorDTO errorDTO;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public MessageResponseDTO save(UserRequestDTO userRequestDto) {
        if (!this.findByEmail(userRequestDto.getEmail())){
            UserModel userModel = DtoMapper.toEntity(userRequestDto);
            String dateFullNow = DateUtil.dateTimeNow();
            userModel.setCreated(dateFullNow);
            userModel.setModified(dateFullNow);
            userModel.setLastLogin(dateFullNow);
            userModel.setIsactive(true);
            UserModel userModelResponse = iUserRepo.save(userModel);

            if (userModelResponse != null && userModelResponse.getId() > 0){
                iPhoneService.save(userRequestDto.getPhones(), userModelResponse.getId());
                messageResponseDTO.setMessage("resource created");
            }
        } else {
            errorDTO.setMensaje("Email is already registered");
            messageResponseDTO.setErrorDTO(errorDTO);
            messageResponseDTO.setStatusCode(HttpStatus.CONFLICT);
        }
        return messageResponseDTO;
    }

    @Override
    public MessageResponseDTO saveAll(List<UserRequestDTO> userRequestDTOList) {
        userRequestDTOList.stream().forEach( userRequestDTO -> {
            if (this.findByEmail(userRequestDTO.getEmail())){
                errorDTO.setMensaje("Email " + userRequestDTO.getEmail() + " is already registered");
                messageResponseDTO.setErrorDTO(errorDTO);
                messageResponseDTO.setStatusCode(HttpStatus.CONFLICT);
            }
        });

        if(messageResponseDTO.getErrorDTO() == null){
            List<UserModel> userModelList = DtoMapper.toEntity(userRequestDTOList);
            userModelList.stream().forEach( userModel -> {
                String dateFullNow = DateUtil.dateTimeNow();
                userModel.setCreated(dateFullNow);
                userModel.setModified(dateFullNow);
                userModel.setLastLogin(dateFullNow);
                userModel.setIsactive(true);
            });

            List<UserModel> userModelListResponse = iUserRepo.saveAll(userModelList);

            if (userModelListResponse != null){
                userModelListResponse.stream().forEach( userModel -> {
                    userRequestDTOList.stream().forEach(userRequestDTO -> {
                        if (userModel.getEmail().equalsIgnoreCase(userRequestDTO.getEmail())){
                            iPhoneService.save(userRequestDTO.getPhones(), userModel.getId());
                            messageResponseDTO.setMessage("resource created");
                        }
                    });
                });
            }
        }
        return messageResponseDTO;
    }

    @Override
    public List<UserResponseDTO> findAll() {

        List<UserModel> userModelList = iUserRepo.findAll();
        if (userModelList != null && userModelList.size() > 0){
            List<UserResponseDTO> userResponseDTOList = DtoMapper.toDTO(userModelList);
            userResponseDTOList.stream().forEach( userResponseDTO -> {
                List<PhoneDTO> phoneDTOList = iPhoneService.findAllByIdUser(userResponseDTO.getId());
                userResponseDTO.setPhones(phoneDTOList);
            });

            return userResponseDTOList;
        }
        return null;
    }

    @Override
    public UserResponseDTO findByNameAndEmail(String name, String email) {

        UserModel userModel = iUserRepo.findByNameAndEmail(name, email);
        if (userModel != null){
            UserResponseDTO userResponseDTO = DtoMapper.toDTO(userModel);
            userResponseDTO.setPhones(iPhoneService.findAllByIdUser(userResponseDTO.getId()));
            return userResponseDTO;
        }

        return null;
    }

    @Override
    public boolean findByEmail(String email) {
        UserModel userModel = iUserRepo.findByEmail(email);
        return userModel != null?true:false;
    }

    @Override
    public MessageResponseDTO delete(int idUser) {
        UserModel userModelDB = this.findById(idUser);
        if (userModelDB != null){
            iUserRepo.deleteById(idUser);
            iPhoneService.delete(idUser);
        }

        messageResponseDTO.setMessage("Delete OK");
        return messageResponseDTO;
    }

    @Override
    public MessageResponseDTO update(int idUser, UserRequestDTO userRequestDTO) {

        UserModel userModelDB = this.findById(idUser);
        boolean emailEquals = userModelDB.getEmail().equalsIgnoreCase(userRequestDTO.getEmail());
        if (userModelDB != null) {
            userModelDB.setEmail(userRequestDTO.getEmail());
            userModelDB.setName(userRequestDTO.getName());
            userModelDB.setPassword(userRequestDTO.getPassword());

            String dateFullNow = DateUtil.dateTimeNow();
            userModelDB.setModified(dateFullNow);
            userModelDB.setLastLogin(dateFullNow);

            if (emailEquals || !this.findByEmail(userRequestDTO.getEmail())){
                UserModel userModelResponse = iUserRepo.save(userModelDB);
                iPhoneService.save(userRequestDTO.getPhones(), idUser);
            } else {
                errorDTO.setMensaje("conflict with email parameters");
                messageResponseDTO.setErrorDTO(errorDTO);
                messageResponseDTO.setStatusCode(HttpStatus.CONFLICT);
                return messageResponseDTO;
            }
            messageResponseDTO.setMessage("Update OK");
        }
        return messageResponseDTO;
    }

    @Override
    public UserModel findById(int idUser) {
        Optional<UserModel> userModel = iUserRepo.findById(idUser);
        return userModel.get();
    }
}
