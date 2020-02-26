package com.nssliu.dataserver.utils.datasourcepoll;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author liuzhiheng
 * @version 1.0
 * @date 2019/11/15 11:36
 * @describe:用于提供数据源连接和归还连接  代理对象方式
 */
public class MyDataSourcePool extends MyDataSourcePoolModel {
    private final List<Connection> POOL;
    {
        Connection conn;
        POOL = new LinkedList<>();
        try{
            for(int i = 0;i<10;i++){
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/esdb",
                        "postgres", "123456");
                //使用代理连接来代表连接
                Connection connProxy = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, new ConnInvo(conn));
                POOL.add(connProxy);
            }
            System.out.println("连接池初始化完成");
        }catch (Exception e){

        }

    }
    @Override
    public Connection getConnection() throws SQLException {
        return POOL.remove(0);
    }
    class ConnInvo implements InvocationHandler {
        private Connection originalConnection;
        public ConnInvo(Connection originalConnection){
            this.originalConnection = originalConnection;
        }

        @Override
        //代理对象，代理的方法，代理方法的参数
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("close")){
                Connection connProxy = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, new ConnInvo(originalConnection));
                POOL.add(connProxy);
                //System.out.println(method.invoke(originalConnection,args));
                return null;
            }
            return method.invoke(originalConnection,args);//执行非close的方法，将返回值返回
        }
    }
}

/**
 * 提供模板
 */
class MyDataSourcePoolModel implements DataSource {

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
