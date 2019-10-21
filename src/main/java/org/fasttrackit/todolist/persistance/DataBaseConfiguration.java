package org.fasttrackit.todolist.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfiguration {

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        InputStream fileInputStream = DataBaseConfiguration.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(fileInputStream);
            Class.forName(properties.getProperty("DB_DRIVER_CLASS"));
            return DriverManager.getConnection(properties.getProperty("DB_URL")
                    ,properties.getProperty("DB_USERNAME")
                    ,properties.getProperty("DB_PASSWORD"));
        } finally {
            //Closing input stream to free up memory
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }

    }

}
