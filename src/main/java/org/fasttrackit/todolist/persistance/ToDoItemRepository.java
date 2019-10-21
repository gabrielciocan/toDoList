package org.fasttrackit.todolist.persistance;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
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
    public void updateToDoItem(long id, boolean done){
        String sql = "UPDATE to_do_item SET done=? WHERE id=?";

        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setBoolean(1,done);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void deleteToDoItem(long id){
        String sql = "DELETE FROM to_do_item WHERE id=?";
        try(Connection connection = DataBaseConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public List<ToDoItem> readToDoItem(){
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
        catch (SQLException | IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return toDoItems;
    }
    public ToDoItem readToDoItem(long id){
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
        catch (SQLException | IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return toDoItem;
    }
}
