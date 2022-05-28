package com.example.wcdpractice.model;

import com.example.wcdpractice.entity.Phone;
import com.example.wcdpractice.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlPhoneModel implements PhoneModel{
    @Override
    public Phone save(Phone phone) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into phones " +
                    "(id, phoneNumber, phoneName, createdAt, updatedAt, status) " +
                    "values " +
                    "(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, phone.getId());
            preparedStatement.setString(2, phone.getPhoneNumber());
            preparedStatement.setString(3, phone.getPhoneName());
            preparedStatement.setString(4, phone.getCreatedAt().toString());
            preparedStatement.setString(5, phone.getUpdatedAt().toString());
            preparedStatement.setInt(6, phone.getStatus());
            System.out.println("Connection success!");
            preparedStatement.execute();
            return phone;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Phone> findAll() {
        List<Phone> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from phones where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 1);
            System.out.println("Connection success!");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String phoneNumber = resultSet.getString("phoneNumber");
                String phoneName = resultSet.getString("phoneName");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int status = resultSet.getInt("status");
                Phone phone = new Phone();
                phone.setId(id);
                phone.setPhoneNumber(phoneNumber);
                phone.setPhoneName(phoneName);
                phone.setCreatedAt(createdAt);
                phone.setUpdatedAt(updatedAt);
                phone.setStatus(status);
                list.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
