package com.example.demo.persistence.dao.impl;

import com.example.demo.persistence.dao.interfaces.IUserDAO;
import com.example.demo.persistence.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em; //administrador de persistencia

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return this.em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void createUser(UserEntity userEntity) {
        this.em.persist(userEntity);
        this.em.flush(); //Nos aseguramos que se sincronice con la db
    }

    @Override
    @Transactional
    public void updateUser(UserEntity userEntity) {
        this.em.merge(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        this.em.remove(userEntity);
    }

}
