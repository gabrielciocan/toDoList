package org.fasttrackit.todolist;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.persistance.ToDoItemRepository;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;
import org.fasttrackit.todolist.transfer.UpdateToDoItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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

//        toDoItemRepository.createToDoItem(createToDoItemRequest);


        UpdateToDoItemRequest updateToDoItemRequest = new UpdateToDoItemRequest();
        updateToDoItemRequest.setDone(true);
        updateToDoItemRequest.setDescription(createToDoItemRequest.getDescription());
        updateToDoItemRequest.setDeadline(createToDoItemRequest.getDeadline());
        toDoItemRepository.updateToDoItem(6,updateToDoItemRequest);
//        toDoItemRepository.deleteToDoItem(4);
        toDoItemRepository.readToDoItem();
        List<ToDoItem> toDoItems = toDoItemRepository.readToDoItem();
        for(ToDoItem toDoItem:toDoItems){
            System.out.println(toDoItem);
        }
        System.out.println("Returning by id:");
        System.out.println(toDoItemRepository.readToDoItem(5));
    }
}
