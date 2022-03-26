package com.angrypig.dao;

import com.angrypig.entity.User;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoTest {

    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);

        System.out.println(userMapper.getAllUsers());
    }

    @Test
    public void test2() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //创建MybatisSqlSessionFactoryBuilder对象, 在mybatis中, 我们使用MybatisSqlSessionFactoryBuilder替代
        //SqlSessionFactoryBuilder
        //SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        MybatisSqlSessionFactoryBuilder sqlSessionFactoryBuilder = new MybatisSqlSessionFactoryBuilder();

        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //通过代理模式创建UserMapper接口的代理实现类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


        //这里的selectList方法不是我们自己在接口定义的, 而是mybatisPlus为我们提供的.
        List<User> users = userMapper.selectList(null);

        System.out.println("结果："+users);
    }
}
