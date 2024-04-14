package dao;

import db.DatabaseConnection;
import dto.History_DTO;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;


public class History_DAO {
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;

    public static void searchHistory(String lat, String lnt) {

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DatabaseConnection.connectDB();

            DateFormatSymbols dfs = new DateFormatSymbols(Locale.KOREAN);
            dfs.setWeekdays(new String[]{
                    "unused",
                    "일요일",
                    "월요일",
                    "화요일",
                    "수요일",
                    "목요일",
                    "금요일",
                    "토요일"
            });
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", dfs);
            String strDate = sdf.format(new Date());

            String sql = " insert into Search_History "
                    + " (lat, lnt, search_dttm) "
                    + " values ( ?, ?, ? )";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);
            preparedStatement.setString(3, strDate);


            preparedStatement.executeUpdate();

            System.out.println("데이터가 삽입 완료되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    public List<History_DTO> searchHistoryList() {
        List<History_DTO> list = new ArrayList<>();

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DatabaseConnection.connectDB();
            String sql = "SELECT * " +
                    "FROM Search_History " +
                    "ORDER BY id DESC";  // 'id' 컬럼을 기준으로 내림차순 정렬

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                History_DTO historyDTO = new History_DTO(
                        resultSet.getInt("id"),
                        resultSet.getString("lat"),
                        resultSet.getString("lnt"),
                        resultSet.getString("search_dttm")
                );
                list.add(historyDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }

        return list;
    }

    public void deleteHistoryList(String id) {

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DatabaseConnection.connectDB();
            String sql = "delete from Search_History where id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }
}