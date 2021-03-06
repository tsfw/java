package com.ithiema.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTest {
    private InputStream in;
    private SqlSession sqlsession;
    private IAccountDao accountDao;
    @Before
    public void init() throws Exception{
        //    读取配置文件
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //    创建SqlSessionfactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //    使用工厂生产SqlSession对象
        sqlsession=factory.openSession();
        //    使用Sqlsession创建dao接口的代理对象
        accountDao=sqlsession.getMapper(IAccountDao.class);
    }
    @After
    public void destory() throws Exception{
        //    6.释放资源
        //    提交事务
        sqlsession.commit();
        sqlsession.close();
        in.close();
    }
    @Test
    public void testFindAll(){
        List<Account> accounts=accountDao.findAll();
        for(Account account:accounts){
            System.out.println(account.getUser());
            System.out.println(account);
        }
    }
    @Test
    public void testFindAllAccountUser(){
        List<AccountUser> aus=accountDao.findAllAccount();
        for(AccountUser au:aus){
            System.out.println(au);
        }
    }


}
