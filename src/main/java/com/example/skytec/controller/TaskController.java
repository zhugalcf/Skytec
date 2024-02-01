package com.example.skytec.controller;

import com.example.skytec.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "task", value = "/task")
public class TaskController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        String clanId = req.getParameter("clanId");
        String gold = req.getParameter("gold");

        TaskService.getInstance().completeTask(Long.parseLong(taskId), Long.parseLong(clanId), Integer.parseInt(gold));
    }
}
