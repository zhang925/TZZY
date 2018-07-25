package com.zzy.mp3;

import java.sql.*;
public class ConnDB{
                Connection conn=null;
                Statement stmt=null;
                ResultSet rs=null;
        public ConnDB(){
                try{
                        Class.forName("com.mysql.jdbc.Driver");
                }catch(java.lang.ClassNotFoundException e){
                        System.err.println(e.getMessage());
                }
        }
 /***************************************************
        *method name:	executeQuery()
        *功能:执行查询操作
        *return value: ResultSet
        *2005-12-05
****************************************************/
        public ResultSet executeQuery(String sql){
                try{
                        conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sunny?user=zhang&password=root");
                        stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                        rs=stmt.executeQuery(sql);
                }catch(SQLException ex){
                        System.err.println(ex.getMessage());
                        }finally{}
                return rs;
        }
/***************************************************
         *method name:	executeUpdate()
         *功能:执行更新操作
         *return value: int
         *2005-12-05
****************************************************/

        public int executeUpdate(String sql){
                int result=0;
                try{
                        conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sunny?user=zhang&password=root");
                        stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                        result=stmt.executeUpdate(sql);
                }catch(SQLException ex){
                        result=0;
                        }finally{}
                return result;
        }
 /***************************************************
        *method name:	close()
        *功能:关闭数据库链接
        *return value: 	void
        *2005-12-05
****************************************************/
       public void close(){
         try {
           if (rs != null) rs.close();
         }
         catch (Exception e) {
           e.printStackTrace(System.err);
         }finally{}
         try {
           if (stmt != null) stmt.close();
         }
         catch (Exception e) {
           e.printStackTrace(System.err);
         }finally{}
         try {
           if (conn != null) {
             conn.close();
           }
         }
         catch (Exception e) {
           e.printStackTrace(System.err);
         }finally{}
       }
}
