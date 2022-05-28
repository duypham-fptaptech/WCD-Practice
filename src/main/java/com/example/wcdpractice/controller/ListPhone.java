package com.example.wcdpractice.controller;

import com.example.wcdpractice.entity.Phone;
import com.example.wcdpractice.model.MySqlPhoneModel;
import com.example.wcdpractice.model.PhoneModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListPhone extends HttpServlet {
    private PhoneModel phoneModel;

    public ListPhone() {
        this.phoneModel = new MySqlPhoneModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Phone> list = phoneModel.findAll();
        req.setAttribute("listPhone", list);
        req.getRequestDispatcher("/listphones.jsp").forward(req, resp);
    }
}
