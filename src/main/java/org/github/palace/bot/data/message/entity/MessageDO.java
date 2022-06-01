package org.github.palace.bot.data.message.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JHY
 * @date 2022/3/29 9:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Long memberId;

    private String message;

    private Date createAt;

}


