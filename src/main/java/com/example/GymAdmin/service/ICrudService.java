package com.example.GymAdmin.service;

import java.util.List;

public interface ICrudService <RQ ,RS,ID> {

    RS create(RQ request);
    RS find(ID id);
    List<RS> findAll();
    RS update(RQ request, ID id);
    void delete(ID id);
}
