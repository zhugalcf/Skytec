package com.example.skytec.controller;

import com.example.skytec.service.DonateService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "donate", value = "/donate")
public class DonateController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String clanId = req.getParameter("clanId");
        String gold = req.getParameter("gold");

        DonateService.getInstance().addGoldToClan(Long.parseLong(userId), Long.parseLong(clanId), Integer.parseInt(gold));
    }
}
