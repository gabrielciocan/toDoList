package org.fasttrackit.todolist;

import org.fasttrackit.todolist.persistance.ToDoItemRepository;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        ToDoItemRepository toDoItemRepository = new ToDoItemRepository();
        CreateToDoItemRequest createToDoItemRequest = new CreateToDoItemRequest();
        createToDoItemRequest.setDescription("This is a description");
        createToDoItemRequest.setDeadline(LocalDate.now().plusWeeks(1));
        toDoItemRepository.createToDoItem(createToDoItemRequest);
    }
}
