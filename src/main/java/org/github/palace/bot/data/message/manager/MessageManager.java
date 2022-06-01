package org.github.palace.bot.data.message.manager;

import org.github.palace.bot.data.MybatisContext;
import org.github.palace.bot.data.common.CommonMapper;
import org.github.palace.bot.data.message.entity.MessageDO;
import org.github.palace.bot.data.message.mapper.MessageMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JHY
 * @date 2022/4/6 9:38
 */
public class MessageManager {
    private static final MessageMapper MESSAGE_MAPPER = MybatisContext.INSTANCE.get(MessageMapper.class);
    private static final CommonMapper COMMON_MAPPER = MybatisContext.INSTANCE.get(CommonMapper.class);

    /**
     * 保存QQ群号
     */
    private static final Set<Long> GROUP_ID_SET = new HashSet<>();

    static {
        // 项目重启时, 加载已有的群号
        GROUP_ID_SET.addAll(MESSAGE_MAPPER.selectGroupIds());
    }

    public void save(Long groupId, MessageDO messageDO) {
        conditionGroupId(groupId);
        MESSAGE_MAPPER.insert(groupId, messageDO);
    }

    private void conditionGroupId(Long groupId) {
        if (!isGroupId(groupId)) {
            GROUP_ID_SET.add(groupId);
            String sql = COMMON_MAPPER.selectCreateTableSql("t_message");
            sql = sql.replace("t_message", "t_message_" + groupId);
            COMMON_MAPPER.exec(sql);
        }
    }


    private boolean isGroupId(Long groupId) {
        return GROUP_ID_SET.contains(groupId);
    }

}
