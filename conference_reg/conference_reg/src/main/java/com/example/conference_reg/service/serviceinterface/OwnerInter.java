package com.example.conference_reg.service.serviceinterface;

import com.example.conference_reg.model.OwnerModel;

public interface OwnerInter {

    OwnerModel registerOwner(OwnerModel ownerModel);

    OwnerModel login(String username, String password);
}
