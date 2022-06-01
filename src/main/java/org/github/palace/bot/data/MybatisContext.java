package org.github.palace.bot.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JHY
 * @date 2022/3/29 9:06
 */
public class MybatisContext {

    public static final MybatisContext INSTANCE = new MybatisContext("mybatis-config.xml");

    private final Map<Class<?>, Object> instancesMap;
    public final SqlSession sqlSession;

    private MybatisContext(String resource) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {

            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        this.sqlSession = sqlSessionFactory.openSession(true);
        this.instancesMap = new HashMap<>(16);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz){
        return (T) instancesMap.computeIfAbsent(clazz, k -> sqlSession.getMapper(clazz));
    }

}
