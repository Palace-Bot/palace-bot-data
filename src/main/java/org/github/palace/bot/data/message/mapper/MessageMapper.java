package org.github.palace.bot.data.message.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.github.palace.bot.data.message.entity.MessageDO;

import java.util.List;

/**
 * @author JHY
 * @date 2022/3/29 9:24
 */
@Mapper
public interface MessageMapper {

    /**
     * 插入一条消息(按照群号分表)
     *
     * @param groupId 群号
     * @param message 消息
     */
    @Insert("insert into t_message_${groupId} (member_id, message, create_at) values (#{message.memberId}, #{message.message}, #{message.createAt})")
    void insert(@Param("groupId") Long groupId, @Param("message") MessageDO message);

    /**
     * 查询所有消息表 群号
     */
    @Select("select substr(name, 11) as group_id from sqlite_master where type='table' and name like 't_message_%'")
    List<Long> selectGroupIds();


    @Select("select * from t_message_${groupId} order by create_at desc")
    List<MessageDO> select(@Param("groupId") Long groupId);

    /**
     * 查询指定 群号 和 成员号 消息
     *
     * @param groupId  群号
     * @param memberId 成员号
     * @return 消息列表
     */
    @Select("select message from t_message_${groupId} where member_id = #{memberId}")
    List<String> selectMessages(@Param("groupId") Long groupId, @Param("memberId") Long memberId);

    /**
     * 查询指定 群号 和 成员号 消息
     *
     * @param groupId  群号
     * @param memberId 成员号
     * @param start    开始时间
     * @param end      结束时间
     * @return 消息列表
     */
    @Select("select message from t_message_${groupId} where member_id = #{memberId} and create_at >= #{start} and create_at <= #{end}")
    List<String> selectMessagesByCreateAt(@Param("groupId") Long groupId, @Param("memberId") Long memberId, @Param("start") Long start, @Param("end") Long end);

}