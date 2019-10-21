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
//        try(Connection connection = DataBaseConfiguration.getConnection();
//            Statement preparedStatement = connection.createStatement()){
//            ResultSet resultSet = preparedStatement.executeQuery(sql);
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("description"));
//            }
//        }
//        catch (SQLException | IOException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
        List<ToDoItem> toDoItems = new ArrayList<>();
        try(Connection connection = DataBaseConfiguration.getConnection();
            Statement preparedStatement = connection.createStatement()){
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                LocalDate deadLine = resultSet.getDate("deadline").toLocalDate();
                boolean done = resultSet.getBoolean("done");

                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setId(id);
                toDoItem.setDeadline(deadLine);
                toDoItem.setDescription(description);
                toDoItem.setDone(done);

                toDoItems.add(toDoItem);

            }
        }
        catch (SQLException | IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return toDoItems;
    }
}
