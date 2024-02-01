package com.example.skytec.controller;

import com.example.skytec.entity.Clan;
import com.example.skytec.service.ClanService;
import com.example.skytec.service.Lock;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "clan", value = "/clan")
public class ClanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        ClanService clanService = ClanService.getInstance();
        printWriter.write(String.valueOf(clanService.get(Long.parseLong(req.getParameter("clanId"))).getGold()));
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String clanId = req.getParameter("clanId");
        Lock.getInstance().writeLock().lock();
        try {
            Clan clan = ClanService.getInstance().get(Long.parseLong(clanId));
            if (clan == null) {
                ClanService.getInstance().addClan(new Clan(Integer.parseInt(clanId), name, 0));
            } else {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(String.format("Clan with id: %s already exist", clanId));
                printWriter.close();
            }
        } finally {
            Lock.getInstance().writeLock().unlock();
        }
    }
}
