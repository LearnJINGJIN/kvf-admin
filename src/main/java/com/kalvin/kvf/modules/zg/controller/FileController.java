package com.kalvin.kvf.modules.zg.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kalvin.kvf.common.constant.UploadPathEnum;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.dto.UploadFileInfo;
import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.common.utils.FileUploadKit;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.zg.entity.File;
import com.kalvin.kvf.modules.zg.service.FileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * 附件管理 前端控制器
 * </p>
 * @since 2021-01-20 14:57:31
 */
@RestController
@RequestMapping("zg/file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;
    @RequiresPermissions("zg:file:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("zg/file");
    }

    @GetMapping(value = "list/data")
    public R listData(File file) {
        Page<File> page = fileService.listFilePage(file);
        return R.ok(page);
    }
    @PostMapping(value = "del")
    public R del(File file) {
        String fileAddrs=file.getFileAddrs();
        if (StrUtil.isBlank(fileAddrs)) {
            throw new KvfException("不存在的文件：" + fileAddrs);
        }
        java.io.File fileAdd=new java.io.File(fileAddrs);
        if(fileAdd.exists()){
            fileAdd.delete();
            fileService.removeById(file.getId());
        }else{
            return R.fail("文件已删除");
        }
        return R.ok();
    }
    @PostMapping(value = "fileUpload")
    public R fileUpload(@RequestParam(value = "file") MultipartFile file, String type, String relevanceCode) {
        if (file == null) {
            return R.fail("没有可上传的文件");
        }
        UploadPathEnum uploadPathEnum = UploadPathEnum.get(type);
        if (uploadPathEnum == null) {
            return R.fail("不存在的上传路径类型：" + type);
        }
        UploadFileInfo upload = FileUploadKit.upload(file, uploadPathEnum);

        if(upload!=null){
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            com.kalvin.kvf.modules.zg.entity.File fileInfo=new com.kalvin.kvf.modules.zg.entity.File();
            fileInfo.setRelevanceCode(relevanceCode);
            fileInfo.setFileName(upload.getName());
            fileInfo.setType(type);
            fileInfo.setFileType(file.getOriginalFilename().substring(begin, last));
            fileInfo.setFileSize(file.getSize()/1024+"kb");
            fileInfo.setFileAddrs(upload.getAbsolutePath());
            fileInfo.setUserId(ShiroKit.getUserId());
            fileService.save(fileInfo);
        }
        return R.ok(upload);
    }
    /**
     * 预览
     */

    @GetMapping(value="showFile")
    public ModelAndView showFile(Long id){
        ModelAndView mv=new ModelAndView("common/fileView");
        File file = fileService.getFileById(id);
        mv.addObject("fileType", file.getFileType());
        mv.addObject("fileUrl",file.getFileAddrs());
        return mv;
    }
}

