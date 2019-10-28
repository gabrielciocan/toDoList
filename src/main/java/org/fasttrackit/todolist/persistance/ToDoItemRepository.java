package org.fasttrackit.todolist.persistance;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;
import org.fasttrackit.todolist.transfer.UpdateToDoItemRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void updateToDoItem(long id, UpdateToDoItemRequest updateToDoItemRequest) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE to_do_item SET done=?,description=?,deadline=? WHERE id=?";

        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setBoolean(1,updateToDoItemRequest.isDone());
            preparedStatement.setString(2,updateToDoItemRequest.getDescription());
            preparedStatement.setDate(3,Date.valueOf(updateToDoItemRequest.getDeadline()));
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
        }
    }
    public void deleteToDoItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM to_do_item WHERE id=?";
        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }

    }
    public List<ToDoItem> readToDoItem() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT * FROM to_do_item";

        List<ToDoItem> toDoItems = new ArrayList<>();
        try(Connection connection = DataBaseConfiguration.getConnection();
            Statement preparedStatement = connection.createStatement()){
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setId(resultSet.getLong("id"));
                toDoItem.setDeadline(resultSet.getDate("deadline").toLocalDate());
                toDoItem.setDescription(resultSet.getString("description"));
                toDoItem.setDone(resultSet.getBoolean("done"));

                toDoItems.add(toDoItem);

            }
        }
        return toDoItems;
    }
    public ToDoItem readToDoItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT * FROM to_do_item WHERE id=?";
        ToDoItem toDoItem = new ToDoItem();

        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                toDoItem.setId(resultSet.getLong("id"));
                toDoItem.setDeadline(resultSet.getDate("deadline").toLocalDate());
                toDoItem.setDescription(resultSet.getString("description"));
                toDoItem.setDone(resultSet.getBoolean("done"));
            }
        }
        return toDoItem;
    }
}
