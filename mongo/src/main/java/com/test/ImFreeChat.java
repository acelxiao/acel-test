package com.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 免费聊天
 *
 * @author yangkeyan
 * @see <a href="">文档</a>
 * @since 5.11.0
 **/
@Data
@Builder
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "im_free_chat")
public class ImFreeChat {

    @Id
    private String id;

    private long uid;

    private long targetUid;

    private int source;

    private LocalDateTime createTime;

    public static String genId(long uid, long targetUid) {
        return uid + "_" + targetUid;
    }

    public void genId() {
        this.id = genId(uid, targetUid);
    }
}
