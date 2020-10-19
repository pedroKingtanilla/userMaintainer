package com.globallogic.userMaintainer.service;

import com.globallogic.userMaintainer.dto.PhoneDTO;
import com.globallogic.userMaintainer.model.PhoneModel;
import com.globallogic.userMaintainer.repository.IPhoneRepo;
import com.globallogic.userMaintainer.util.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements IPhoneService {
    Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);

    @Autowired
    IPhoneRepo iPhoneRepo;

    @Override
    public List<PhoneDTO> findAllByIdUser(int idUser) {
        List<PhoneModel> phoneModelList = iPhoneRepo.findAllByIdUser(idUser);
        return DtoMapper.toPhoneDTO(phoneModelList);
    }

    @Override
    public List<PhoneDTO> save(List<PhoneDTO> phoneDTOList, int idUser) {

        List<PhoneModel> phoneModelList = iPhoneRepo.saveAll(DtoMapper.toEntity(phoneDTOList, idUser));
        phoneDTOList.stream().forEach(phoneDTO -> {
            logger.info("phoneDTO", phoneDTO.toString());
            logger.info(phoneDTO.getCityCode());
        });
        return null;
    }

    @Override
    public void delete(int idUser){
        iPhoneRepo.deleteByIdUser(idUser);
    }
}
