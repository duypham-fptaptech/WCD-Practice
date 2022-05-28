package com.example.wcdpractice.controller;

import com.example.wcdpractice.entity.Phone;
import com.example.wcdpractice.model.MySqlPhoneModel;
import com.example.wcdpractice.model.PhoneModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class CreatePhone extends HttpServlet {
    private PhoneModel phoneModel;

    public CreatePhone() {
        this.phoneModel = new MySqlPhoneModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form.
        req.setAttribute("phone", new Phone());
        req.getRequestDispatcher("/addphone.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String phoneNumber = req.getParameter("phoneNumber");
        String phoneName = req.getParameter("phoneName");
        Phone phone = new Phone(phoneNumber, phoneName);
        HashMap<String, String> errors = new HashMap<>();
        // validate dữ liệu theo kiểu cùi bắp.
        if (phoneNumber == null || phoneNumber.length() == 0) {
            errors.put("phoneNumber", "Please enter phone number");
        }
        if (phoneName == null || phoneName.length() == 0) {
            errors.put("phoneName", "Please enter phone name");
        }
        if (errors.size() > 0) {
            req.setAttribute("phone", phone);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/addphone.jsp").forward(req, resp);
            return;
        }
        if (phoneModel.save(phone) != null) {
            resp.sendRedirect("/list-phone");
        } else {
            req.getRequestDispatcher("/addphone.jsp").forward(req, resp);
        }
    }
}
