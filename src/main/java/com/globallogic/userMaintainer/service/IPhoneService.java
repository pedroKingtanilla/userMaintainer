package com.globallogic.userMaintainer.service;

import com.globallogic.userMaintainer.dto.PhoneDTO;

import java.util.List;

public interface IPhoneService {
    List<PhoneDTO> findAllByIdUser(int idUser);
    List<PhoneDTO> save(List<PhoneDTO> phoneDTOList, int idUser);
    void delete(int idUser);

}
