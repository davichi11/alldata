package cn.datax.common.mybatis.config;

import cn.datax.common.core.DataConstant;
import cn.datax.common.utils.SecurityUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class DataMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, "status", String.class, DataConstant.EnableState.ENABLE.getKey());
		this.strictInsertFill(metaObject, "createBy", String.class, "1");
		this.strictInsertFill(metaObject, "updateBy", String.class, "1");
		boolean bolCreateDept = metaObject.hasSetter("createDept");
		if (bolCreateDept) {
			this.strictInsertFill(metaObject, "createDept", String.class, "1");
		}
		boolean bolFlowStatus = metaObject.hasSetter("flowStatus");
		if (bolFlowStatus) {
			this.strictInsertFill(metaObject, "flowStatus", String.class, DataConstant.AuditState.WAIT.getKey());
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtil.getUserId());
	}
}
