package com.example.conference_reg.service;

import com.example.conference_reg.conversions.EntityToModel;
import com.example.conference_reg.conversions.ModelToEntity;
import com.example.conference_reg.entity.Owner;
import com.example.conference_reg.model.OwnerModel;
import com.example.conference_reg.repository.OwnerRepository;
import com.example.conference_reg.service.serviceinterface.OwnerInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class OwnerService implements OwnerInter {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityToModel entityToModel;
    @Autowired
    private ModelToEntity modelToEntity;

    public OwnerModel registerOwner(OwnerModel ownerModel) {
        Owner owner = modelToEntity.convertToEntity3(ownerModel);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        Owner savedOwner = ownerRepository.save(owner);
        return entityToModel.convertToModel3(savedOwner);
    }
    public OwnerModel login(String username,String password) {
        Owner owner = ownerRepository.findByUsernameAndPassword(username,password);
        return entityToModel.convertToModel3(owner);
    }




//    public OwnerModel convertToModel3(Owner owner) {
//        if (owner == null) {
//            return null;
//        }
//        OwnerModel ownerModel = new OwnerModel();
//        ownerModel.setName(owner.getName());
//        ownerModel.setRole(owner.getRole());
//        ownerModel.setUsername(owner.getUsername());
//        ownerModel.setPassword(owner.getPassword());
//        ownerModel.setEmail(owner.getEmail());
//        return ownerModel;
//    }
//    public Owner convertToEntity3(OwnerModel ownerModel) {
//        if (ownerModel == null) {
//            return null;
//        }
//        Owner owner = new Owner();
//        owner.setName(ownerModel.getName());
//        owner.setRole(ownerModel.getRole());
//        owner.setUsername(ownerModel.getUsername());
//        owner.setPassword(ownerModel.getPassword());
//        owner.setEmail(ownerModel.getEmail());
//        return owner;
//    }
}
