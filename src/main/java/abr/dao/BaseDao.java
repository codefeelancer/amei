package abr.dao;

import abr.bean.Webpage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 基本Dao操作
 *
 * @author Y.H.
 * @create 2017-11-21 21:34
 **/
@Repository
public class BaseDao {
    @Resource
    private DataSource dataSource;

    private Logger log = LoggerFactory.getLogger(BaseDao.class);

    /**
     * 从资源池获取数据库连接
     * @return 连接
     */
    public Connection retriveConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            System.err.println("ERROR:未能打开数据库连接");
            log.error("未能打开数据库连接");
            return null;
        }
    }

    /**
     * 一次更新操作，包括插入、修改、删除三种类型的操作
     *
     * @param sql
     * @return 更新成功与否
     */
    public boolean update(String sql){
        Connection connection = retriveConnection();
        Statement statement = null;
        ResultSet rs = null;
        boolean result = false;
        if (connection != null){
            try {
                statement = connection.createStatement();
                int rownumUpdated = statement.executeUpdate(sql);
                result = rownumUpdated > 0 ? true : false;

            } catch (SQLException e) {
                System.err.println("ERROR:更新数据库失败，语句："+sql);
                e.printStackTrace();

                log.error("更新数据库失败，语句："+sql);
                StackTraceElement[] stacks = e.getStackTrace();
                for (StackTraceElement line : stacks){
                    log.error(line.toString());
                }


            } finally {
                closeConnection(rs,statement,connection);

            }
        }
        return result;
    }

    /**
     * 关闭该连接相关的资源
     *
     * @param rs
     * @param statement
     * @param connection
     */
    public void closeConnection(ResultSet rs,Statement statement,Connection connection){
        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("ERROR:数据库连接关闭失败");
            log.error("ERROR:数据库连接关闭失败");
        }
    }
}
