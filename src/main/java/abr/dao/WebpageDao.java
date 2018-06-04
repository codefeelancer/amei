package abr.dao;

import abr.bean.Webpage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WebpageDao {

    @Autowired
    private BaseDao baseDao;

    private Logger log = LoggerFactory.getLogger(WebpageDao.class);

    /**
     * 根据页面的路径path查询页面
     * @param path
     * @return
     */
    public Webpage queryPageByPath(String path){
        String sql = "SELECT * FROM WEBPAGE WHERE path ='"+path+"'";
        List<Webpage>  list = queryWebpageList(sql);
        if (list != null && list.size() != 0){
            return list.get(0);
        }else{
            return  null;
        }
    }

    private List<Webpage> queryWebpageList(String sql){
        System.out.print(sql);
        log.info(sql);
        Connection connection = baseDao.retriveConnection();
        Statement statement = null;
        ResultSet rs = null;
        List<Webpage> result = new ArrayList<>();
        if (connection != null){
            try {
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()){
                    Webpage webpage = new Webpage();
                    String html = null;
                    try {
                        Reader r = rs.getCharacterStream("html");
                        if (r != null) {
                            BufferedReader br = new BufferedReader(r);
                            html = br.readLine();
                        }
                        r.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    webpage.setHtml(html);
                    webpage.setPath(rs.getString("path"));
                    webpage.setTitle(rs.getString("title"));
                    result.add(webpage);
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


	public List<Webpage> queryPagesByKeyword(String keyword) {
		String sql = "SELECT * FROM WEBPAGE WHERE html LIKE '%"+keyword+"%'";
        List<Webpage>  list = queryWebpageList(sql);
        return list;
	}


	public List<Webpage> queryList() {
		String sql = "SELECT path,title FROM WEBPAGE";
        System.out.print(sql);
        log.info(sql);
        Connection connection = baseDao.retriveConnection();
        Statement statement = null;
        ResultSet rs = null;
        List<Webpage> result = new ArrayList<>();
        if (connection != null){
            try {
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()){
                    Webpage webpage = new Webpage();
                    webpage.setPath(rs.getString("path"));
                    webpage.setTitle(rs.getString("title"));
                    result.add(webpage);
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


	public boolean addWebpage(String title, String path, String html) {
		String sql = "INSERT INTO WEBPAGE(path,title,html) values('"+path+"','"+title+"','"+html+"')";
		return baseDao.update(sql);
	}


	public boolean modifyWebpage(String title, String html,String path) {
		String sql = "UPDATE WEBPAGE SET html = '"+html+"', title = '"+title+"' WHERE path = '"+path+"'";
		return baseDao.update(sql);
	}
}
