package com.globallogic.userMaintainer.repository;

import com.globallogic.userMaintainer.model.PhoneModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPhoneRepo extends JpaRepository<PhoneModel, Integer> {

    List<PhoneModel> findAllByIdUser(int idUser);
    void deleteByIdUser(int idUser);
}
