package com.globallogic.userMaintainer.repository;

import com.globallogic.userMaintainer.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo  extends JpaRepository<UserModel, Integer> {

    UserModel findByNameAndEmail(String name, String email);
    UserModel findByEmail(String email);
}
