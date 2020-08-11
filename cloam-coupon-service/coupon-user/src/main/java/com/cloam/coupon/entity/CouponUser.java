package com.cloam.coupon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <h1>用户实体表<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_user")
//@JsonSerialize(using = ) 自定义序列化器
public class CouponUser implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 昵称
     */
    @Column(name = "nick_name", nullable = false)
    private String nickName;

    /**
     * 用户名
     */
    @Column(name = "user_name", nullable = false)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 性别
     */
    @Column(name = "gender", nullable = false)
    private String gender;

    /**
     * 头像
     */
    @Column(name = "portrait", nullable = false)
    private String portrait;

    /**
     * 盐
     */
    @Column(name = "salt", nullable = false)
    private String salt;

    /**
     * 是否可用
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "createTime", nullable = false)
    private Timestamp create_time;

    /**
     * 修改时间
     */
    @Column(name = "updateTime", nullable = false)
    private Timestamp update_time;
}
