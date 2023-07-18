package cn.datax.service.data.masterdata.service.impl;

import cn.datax.common.exception.DataException;
import cn.datax.service.data.masterdata.api.entity.ModelColumnEntity;
import cn.datax.service.data.masterdata.api.entity.ModelDataEntity;
import cn.datax.service.data.masterdata.api.entity.ModelEntity;
import cn.datax.service.data.masterdata.service.DataImportService;
import cn.datax.service.data.masterdata.service.ModelColumnService;
import cn.datax.service.data.masterdata.service.ModelDataService;
import cn.datax.service.data.masterdata.service.ModelService;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 数据导入服务实现
 *
 * @author huchunliang
 * @date 2023/07/18
 */
@Slf4j
@Service
public class DataImportServiceImpl implements DataImportService {
    @Autowired
    private ModelService modelService;
    @Autowired
    private ModelDataService modelDataService;
    @Autowired
    private ModelColumnService modelColumnService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(MultipartFile file, HttpServletResponse response) {
        //上传成功后获取文件元数据

        String fileUrl = "/Users/huchunliang/Downloads/ld" + File.separator + file.getOriginalFilename();
        try {
            FileUtil.writeFromStream(file.getInputStream(), fileUrl);
        } catch (IOException e) {
            throw new DataException(e);
        }
        try (Response resp = new OkHttpClient().newCall(new Request.Builder().url("http://localhost:8001/metaData?file_url=" + fileUrl).get().build()).execute()) {
            if (!resp.isSuccessful()) {
                log.error("获取文件元数据失败");
                return;
            }
            if (resp.body() == null) {
                log.error("获取文件元数据为空");
                return;
            }
            JSONObject body = JSON.parseObject(resp.body().string());
            Map<String, String> metaData = (Map<String, String>) body.get("meta_data");
            metaData.put("create_by", "创建人");
            metaData.put("create_time", "创建时间");
            metaData.put("update_by", "更新人");
            metaData.put("update_time", "更新时间");
            metaData.put("id", "主键");
            List<Map<String, Object>> fileData = (List<Map<String, Object>>) body.get("file_data");
            String modelId = UUID.randomUUID().toString().replace("-", "");
            //生成Model
            ModelEntity modelData = new ModelEntity();
            modelData.setModelName(file.getOriginalFilename());
            String tableName = body.getString("table_name");
            if (StringUtils.isBlank(tableName)) {
                throw new DataException("表名不能为空");
            }
            modelData.setModelLogicTable(tableName);
            modelData.setModelPhysicalTable(tableName);
            modelData.setFlowStatus("4");
            modelData.setCreateDept("1");
            modelData.setId(modelId);
            modelData.setCreateTime(LocalDateTime.now());
            modelData.setCreateBy("1");
            modelData.setStatus("1");
            modelService.save(modelData);

            //生成列信息
            List<ModelColumnEntity> columns = new ArrayList<>();
            metaData.forEach((k, v) -> {
                ModelColumnEntity modelColumn = new ModelColumnEntity();
                modelColumn.setModelId(modelId);
                modelColumn.setColumnName(k);
                modelColumn.setColumnComment(v);
                modelColumn.setIsPk("0");
                modelColumn.setIsList("1");
                modelColumn.setIsDetail("1");
                modelColumn.setId(UUID.randomUUID().toString().replace("-", ""));
                modelColumn.setCreateTime(LocalDateTime.now());
                modelColumn.setCreateBy("1");
                modelColumn.setStatus("1");
                modelColumn.setIsSystem("0");
                columns.add(modelColumn);
            });
            modelColumnService.saveBatch(columns);

            //创建表
            modelService.createTable(modelId);
            //插入数据
            fileData.forEach(fd -> {
                fd.put("fileUrl", body.getString("file_url"));
                fd.put("fileName", body.getString("file_name"));
                fd.put("fileType", body.getString("file_type"));
                ModelDataEntity dataEntity = new ModelDataEntity();
                dataEntity.setTableName(modelData.getModelPhysicalTable());
                dataEntity.setDatas(fd);
                dataEntity.setId(UUID.randomUUID().toString().replace("-", ""));
                modelDataService.addModelData(dataEntity);
            });
        } catch (IOException e) {
            throw new DataException("获取文件元数据异常", e);
        }
    }
}
