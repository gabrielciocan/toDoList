package org.fasttrackit.todolist;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.persistance.ToDoItemRepository;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

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
//        toDoItemRepository.updateToDoItem(2,true);
//        toDoItemRepository.deleteToDoItem(4);
        List<ToDoItem> toDoItems = toDoItemRepository.readToDoItem();
        for(ToDoItem toDoItem:toDoItems){
            System.out.println(toDoItem);
        }
    }
}
