package org.fasttrackit.todolist.persistance;

import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToDoItemRepository {

    public void createToDoItem(CreateToDoItemRequest createToDoItemRequest) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO to_do_item (description, deadline) VALUES(?, ?)";
        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,createToDoItemRequest.getDescription());
            preparedStatement.setDate(2, Date.valueOf(createToDoItemRequest.getDeadline()));

            preparedStatement.executeUpdate();
        }
    }
}
