package abr.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import abr.bean.User;

@Repository
public class UserDao {
	@Autowired
    private BaseDao baseDao;

    private Logger log = LoggerFactory.getLogger(UserDao.class);

    /**
     * 查询登录用户名密码是否正确
     * @return
     */
    public User queryUser(String username,String password){
        String sql = "SELECT * FROM T_USER WHERE username ='"+username+"' and password = '"+password+"'";
        List<User>  list = queryUserList(sql);
        if (list != null && list.size() != 0){
            return list.get(0);
        }else{
            return null;
        }
    }
    
    
    private List<User> queryUserList(String sql){
        System.out.print(sql);
        log.info(sql);
        Connection connection = baseDao.retriveConnection();
        Statement statement = null;
        ResultSet rs = null;
        List<User> result = new ArrayList<>();
        if (connection != null){
            try {
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()){
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setId(rs.getInt("id"));
                    user.setPriority(rs.getString("priority"));
                    result.add(user);
                }

            } catch (SQLException e) {
                System.err.println("ERROR:查询，语句："+sql);
                e.printStackTrace();

//                log.error("未能创建查询，语句："+sql);
//                StackTraceElement[] stacks = e.getStackTrace();
//                for (StackTraceElement line : stacks){
//                    log.error(line.toString());
//                }

            } finally {
                baseDao.closeConnection(rs,statement,connection);

            }
        }
        return result;
    }

}
