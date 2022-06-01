package org.github.palace.bot.data.message.service.impl;

import org.github.palace.bot.data.message.entity.MessageDO;
import org.github.palace.bot.data.message.manager.MessageManager;
import org.github.palace.bot.data.message.service.MessageService;

/**
 * @author JHY
 * @date 2022/4/6 9:39
 */
public class MessageServiceImpl implements MessageService {
    private final MessageManager messageManager;

    public MessageServiceImpl() {
        this.messageManager = new MessageManager();
    }

    @Override
    public void save(Long groupId, MessageDO messageDO) {
        messageManager.save(groupId, messageDO);
    }

}
