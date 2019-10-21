package org.fasttrackit.todolist.web;

import org.fasttrackit.todolist.service.ToDoItemService;

import javax.servlet.http.HttpServlet;

public class ToDoItemServlet extends HttpServlet {

    private ToDoItemService toDoItemService = new ToDoItemService();


}