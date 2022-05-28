package com.example.wcdpractice.model;

import com.example.wcdpractice.entity.Phone;

import java.util.List;

public interface PhoneModel {
    Phone save(Phone phone); // lưu thông tin.
    List<Phone> findAll();
}
