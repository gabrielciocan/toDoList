package org.fasttrackit.todolist.service;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.persistance.ToDoItemRepository;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;
import org.fasttrackit.todolist.transfer.UpdateToDoItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ToDoItemService {

    private ToDoItemRepository toDoItemRepository = new ToDoItemRepository();

    public void createToDoItem(CreateToDoItemRequest createToDoItemRequest) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating to-do-item: " + createToDoItemRequest);
        toDoItemRepository.createToDoItem(createToDoItemRequest);
    }

    public void updateToDoItem(long id, UpdateToDoItemRequest updateToDoItemRequest) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating to-do-item: " + id + " " + updateToDoItemRequest);
        toDoItemRepository.updateToDoItem(id, updateToDoItemRequest);
    }

    public void deleteToDoItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting to-do-item: " + id);
        toDoItemRepository.deleteToDoItem(id);
    }

    public List<ToDoItem> readToDoItem() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving to-do-items!");
        return toDoItemRepository.readToDoItem();
    }
}
