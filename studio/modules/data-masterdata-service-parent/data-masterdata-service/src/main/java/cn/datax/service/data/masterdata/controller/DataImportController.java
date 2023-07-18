package cn.datax.service.data.masterdata.controller;

import cn.datax.common.core.R;
import cn.datax.service.data.masterdata.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 数据导入控制器
 *
 * @author huchunliang
 * @date 2023/07/18
 */
@RestController
@RequestMapping("/import")
public class DataImportController {

    @Autowired
    private DataImportService dataImportService;

    @PostMapping("/upload")
    public R uploadFile(MultipartFile file, HttpServletResponse response) {
        dataImportService.upload(file, response);
        return R.ok();
    }
}
