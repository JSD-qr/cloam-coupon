package com.cloam.coupon.common;

import com.cloam.coupon.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>数据表Id生成器<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 10:57
 */
@Slf4j
@Component
public class UserIdWorker {

    private static final Long WORKERId = 1L;
    private static final Long DATACENTERID = 1L;
    private static final Long SEQUENCE = 1000L;

    private static IdWorker worker = new IdWorker(WORKERId, DATACENTERID, SEQUENCE);


    /**
     * 获取id主键
     *
     * @return id
     */
    public static Long getId() {
        return worker.nextId();
    }

    /**
     * 获取批量id
     *
     * @param size 数量
     * @return
     */
    public static List<Long> getIds(int size) {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ids.add(getId());
        }

        return ids;
    }


}
