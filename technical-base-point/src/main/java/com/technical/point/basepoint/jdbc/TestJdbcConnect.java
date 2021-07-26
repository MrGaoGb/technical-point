package com.technical.point.basepoint.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: Mr.Gao
 * @date: 2021/7/26 17:46
 * @description:新增JDBC连接测试代码
 */
public class TestJdbcConnect {


    /**
     * before:	1627302680289
     * after:		1627302680419
     * Create Costs:		130 ms (创建conn耗时时间)
     * Exec Costs:		    17 ms  (执行SQL语句耗时时间)
     * <p>
     * <p>
     * 执行结果分析:
     * 创建一个Connection连接耗时130ms,设想一下用户的每个请求都需要操作数据库，在高并发的场景下，100000个用户并发操作的话，对于计算机而言，仅仅
     * 创建Connection对象不包括业务逻辑的时间就要耗费100000 * 130ms = 13000000ms = 13000s = 3.61h（小时）。对于现在的高可用高性能高并发
     * 的快速服务响应的宗旨，这显然是不理想的，也就是不可采用的。
     * </p>
     * <p>
     * 问题分析:
     * 创建一个Connection连接就耗费那么长的时间，其底层就相当于和数据库建立通信，建立通信后，往往只需要执行一个简单的SQL，然后就要释放掉，这是非常
     * 浪费资源的。
     * </p>
     * <p>
     * 解决方案:
     * 对于频繁的跟数据库交互的应用程序，可以在创建Connection对象并访问完数据库之后不释放掉conn资源，而是将conn放到内存中，当下次需要操作数据库时
     * 直接从内存中取出con对象，就不需要创建了，这样就极大的节省的创建conn资源的浪费，由于内存是非常有限和宝贵的，这又对内存中的conn对象怎么有效的
     * 维护提出了很高的要求，我们将内存中存放conn对象的容器叫做连接池。
     * </p>
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String sql = "select * from user_info where id < ? and id >= ?";
        Class.forName("org.postgresql.Driver");
        PreparedStatement st = null;
        ResultSet rs = null;

        long beforeTimeOffset = -1L; //创建Connection对象前时间
        long afterTimeOffset = -1L; //创建Connection对象后时间
        long executeTimeOffset = -1L; //创建Connection对象后时间

        Connection con = null;
        Class.forName("org.postgresql.Driver");

        beforeTimeOffset = System.currentTimeMillis();
        System.out.println("before:\t" + beforeTimeOffset);
        String url = "jdbc:postgresql://192.168.0.118:5432/postgres?autosave=conservative";
        String userName = "postgres";
        String pwd = "postgres123";
        con = DriverManager.getConnection(url, userName, pwd);

        afterTimeOffset = System.currentTimeMillis();
        System.out.println("after:\t\t" + afterTimeOffset);
        System.out.println("Create Costs:\t\t" + (afterTimeOffset - beforeTimeOffset) + " ms");

        st = con.prepareStatement(sql);
        //设置参数
        st.setInt(1, 101);
        st.setInt(2, 0);
        //查询，得出结果集
        rs = st.executeQuery();
//        数据转化
//        List<UserInfoPo> userInfoPoList = new ArrayList<>();
//        while (rs.next()) {
//            UserInfoPo userInfoPo = new UserInfoPo();
//            userInfoPo.setId(rs.getLong("id"));
//            userInfoPo.setUserName(rs.getString("user_name"));
//            userInfoPo.setAddress(rs.getString("address"));
//            userInfoPo.setPhoneNo(rs.getString("phone_no"));
//            userInfoPoList.add(userInfoPo);
//        }
//        System.out.println(userInfoPoList);
        executeTimeOffset = System.currentTimeMillis();
        System.out.println("Exec Costs:\t\t" + (executeTimeOffset - afterTimeOffset) + " ms");
    }
}
