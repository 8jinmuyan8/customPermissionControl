package com.lx.project.mapper;

import com.lx.project.entity.SysAccessLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统访问日志 Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2018-12-23
 */
@Mapper
@Repository
public interface SysAccessLogMapper extends BaseMapper<SysAccessLog> {
    void updateEndTime(@Param("localDateTime")LocalDateTime localDateTime,@Param("id") int id);

    void updateAccountById(@Param("id") int id, @Param("account") String account);
}
