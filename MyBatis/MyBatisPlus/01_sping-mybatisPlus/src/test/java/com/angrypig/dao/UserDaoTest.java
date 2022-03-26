package com.angrypig.dao;

import com.angrypig.entity.User;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserDaoTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){

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

    @Test
    public void test3(){
        User user = new User();
        user.setUsername("矮人狙击手");
        user.setPassword("dfjigu@#%$");
        user.setEmail("jfkdsjf@dkf.com");

        int i = userMapper.insert(user);

        System.out.println(i);
        System.out.println(user);

    }
}
