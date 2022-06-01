package org.github.palace.bot.data.common;

import org.apache.ibatis.annotations.Select;

/**
 * @author JHY
 * @date 2022/4/6 10:01
 */
public interface CommonMapper {

    /**
     * 查询创表 sql
     *
     * @param tableName 表名
     * @return sql
     */
    @Select("select sql from sqlite_master where type = 'table' and name = #{tableName}")
    String selectCreateTableSql(String tableName);

    /**
     * 执行sql(不使用占位符)
     *
     * @param sql sql
     */
    @Select("${sql}")
    void exec(String sql);

}
