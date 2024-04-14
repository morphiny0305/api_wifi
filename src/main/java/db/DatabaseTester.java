package db;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTester {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // 데이터베이스 연결
            connection = DatabaseConnection.connectDB();

            // 연결이 성공했는지 확인
            if (connection != null) {
                System.out.println("데이터베이스 연결 성공!");
            } else {
                System.out.println("데이터베이스 연결 실패!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 연결 종료
            DatabaseConnection.close(connection, null, null);
        }
    }
}