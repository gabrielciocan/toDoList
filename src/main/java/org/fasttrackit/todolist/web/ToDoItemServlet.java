package org.fasttrackit.todolist.web;

import org.fasttrackit.todolist.config.ObjectMapperConfiguration;
import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.service.ToDoItemService;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;
import org.fasttrackit.todolist.transfer.UpdateToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/to-do-items")
public class ToDoItemServlet extends HttpServlet {

    private ToDoItemService toDoItemService = new ToDoItemService();


    //endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateToDoItemRequest createToDoItemRequest =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),CreateToDoItemRequest.class);

        try {
            toDoItemService.createToDoItem(createToDoItemRequest);
        } catch (SQLException|ClassNotFoundException e) {
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UpdateToDoItemRequest updateToDoItemRequest =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),UpdateToDoItemRequest.class);
        try{
            toDoItemService.updateToDoItem(id,updateToDoItemRequest);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        try{
            toDoItemService.deleteToDoItem(id);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String response = null;
            if(id == null){
                List<ToDoItem> toDoItems = toDoItemService.readToDoItem();
                response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(toDoItems);

            }
            else{
                resp.getWriter().print("No such id found!");
            }
            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }
}