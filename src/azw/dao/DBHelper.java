package azw.dao;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class DBHelper {
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String DBNAME="azw";  //数据库名称
	private static final String USER="root";    //连接的用户名
    private static final String PASSWORD="root";  //连接的用户名的密码
    private static final String ENCODING="UTF-8";       //字符编码方式
    private static final String URL="jdbc:mysql://localhost:3306/"+DBNAME+"?useUnicode=true&characterEncoding="+ENCODING;

       
    /**
     * 连接数据库
     * @return 链接数据库对象
     */
    public Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
     
    /**
     * 释放相应的资源
     * @param rs
     * @param pstmt
     * @param conn
     */
    public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    /**
     * 此方法可以完成增删改所有的操作
     * @param sql
     * @param params
     * @return true or false
     */
    public boolean executeUpdate(String sql,List<Object> params){
        int res=0;//受影响的行数
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);//装载sql语句
            if(params!=null){
                //加入有？占位符，在执行之前把？占位符替换掉
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            res=pstmt.executeUpdate();
             
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeAll(rs, pstmt, conn);
        }
        return res>0?true:false;
    }
     
    /**
     * 使用泛型方法和反射机制进行封装
     * @param sql
     * @param params
     * @param cls
     * @return
     */
    public <T> List<T> executeQuery(String sql,List<Object> params,Class<T> cls) throws Exception{
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<T> data=new ArrayList<T>();
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);//装载sql语句
            if(params!=null){
                //加入有？占位符，在执行之前把？占位符替换掉
                for(int i=0;i<params.size();i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs=pstmt.executeQuery();
            //把查询出来的记录封装成对应的实体类对象
            ResultSetMetaData rsd=rs.getMetaData();//获得列对象,通过此对象可以得到表的结构，包括，列名，列的个数，列的数据类型
            while(rs.next()){
                T m=cls.newInstance();
                for(int i=0;i<rsd.getColumnCount();i++){
                    String col_name=rsd.getColumnName(i+1);//获得列名
                    Object value=rs.getObject(col_name);//获得列所对应的值
                    Field field=cls.getDeclaredField(col_name);
                    field.setAccessible(true);//给私有属性设置可访问权
                    field.set(m, value);//给对象的私有属性赋值
                }
                data.add(m);
            }
             
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeAll(rs, pstmt, conn);
        }
        return data;
    }
}