package org.github.palace.bot.data.message.service;

import org.github.palace.bot.data.message.entity.MessageDO;

/**
 * @author JHY
 * @date 2022/4/6 9:39
 */
public interface MessageService {

    void save(Long groupId, MessageDO messageDO);
}
