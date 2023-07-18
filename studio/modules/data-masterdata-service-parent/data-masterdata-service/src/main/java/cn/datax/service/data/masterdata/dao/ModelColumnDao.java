package cn.datax.service.data.masterdata.dao;

import cn.datax.common.base.BaseDao;
import cn.datax.service.data.masterdata.api.entity.ModelColumnEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 主数据模型列信息表 Mapper 接口
 * </p>
 *
 * @author AllDataDC
 * @date 2022-11-26
 */
@Mapper
public interface ModelColumnDao extends BaseDao<ModelColumnEntity> {

    /**
     * 查询通过表名字
     *
     * @param tableName 表名
     * @return {@link List}<{@link ModelColumnEntity}>
     */
    List<ModelColumnEntity> selectByTableName(String tableName);
}
