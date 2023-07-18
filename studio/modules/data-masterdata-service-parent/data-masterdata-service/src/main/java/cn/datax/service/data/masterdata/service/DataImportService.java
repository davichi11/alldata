package cn.datax.service.data.masterdata.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 数据导入服务
 *
 * @author huchunliang
 * @date 2023/07/18
 */
public interface DataImportService {
    /**
     * 上传
     *
     * @param file     文件
     * @param response 响应
     */
    void upload(MultipartFile file, HttpServletResponse response);
}
