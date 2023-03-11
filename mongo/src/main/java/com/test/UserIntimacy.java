package com.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * @description: 亲密值
 * @author: rickiyang
 * @date: 2022/3/1
 **/
@Data
@Builder
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_intimacy")
public class UserIntimacy {


    @Id
    private String id;

    private Long uid;

    private Long targetUid;

    private String targetUidAlias;

    private Long intimacy;

    private Date updateTime;

    public static String genId(long uid, long targetUid) {
        return uid + "_" + targetUid;
    }
}
