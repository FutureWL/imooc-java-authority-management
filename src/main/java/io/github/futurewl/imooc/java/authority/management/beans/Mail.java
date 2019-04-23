package io.github.futurewl.imooc.java.authority.management.beans;

import lombok.*;

import java.util.Set;

/**
 * 功能描述：
 *
 * @author weilai create by 2019-04-23:10:16
 * @version 1.0
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;

    private String message;

    private Set<String> receivers;

}
