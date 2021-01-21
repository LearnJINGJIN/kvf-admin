package com.kalvin.kvf.common.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.kalvin.kvf.common.constant.Constants;
import com.kalvin.kvf.common.constant.UploadPathEnum;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.dto.UploadFileInfo;
import com.kalvin.kvf.common.exception.KvfException;
import com.kalvin.kvf.common.utils.FileUploadKit;
import com.kalvin.kvf.common.utils.HttpServletContextKit;
import com.kalvin.kvf.common.utils.ShiroKit;
import com.kalvin.kvf.modules.sys.service.IUserService;
import com.kalvin.kvf.modules.zg.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 【作用】通用控制层<br>
 * 【说明】（无）
 * @author Kalvin
 * @Date 2019/5/9 20:20
 */
@Slf4j
@RestController
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "select/user")
    public ModelAndView selectUser() {
        return new ModelAndView("common/select_user");
    }

    @PostMapping(value = "fileUpload")
    public R fileUpload(@RequestParam(value = "file") MultipartFile file, String type) {
        if (file == null) {
            return R.fail("没有可上传的文件");
        }
        UploadPathEnum uploadPathEnum = UploadPathEnum.get(type);
        if (uploadPathEnum == null) {
            return R.fail("不存在的上传路径类型：" + type);
        }
        return R.ok(FileUploadKit.upload(file, uploadPathEnum));
    }

    /**
     * jingjin 2021年1月20日17:23:38
     * @param filePath
     * @param fileName
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/downloadFile")
     public void downloadFile(String filePath,String fileName,HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StrUtil.isBlank(filePath)) {
            throw new KvfException("不存在的文件：" + filePath);
        }
        InputStream fis = null;
        File file = new File(filePath);
        try {
             String filename = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(file));
            byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB
            int len = 0;
            // 清空response,省的影响
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            while ((len = fis.read(bytes)) != -1) {
                toClient.write(bytes, 0, len);
            }
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * 访问项目外部静态图片
     */
    @GetMapping(value = "static/{fileType}/{yyyyMMdd}/{filename}")
    public void staticImage(@PathVariable String fileType, @PathVariable String yyyyMMdd, @PathVariable String filename) {
        String basePath = System.getProperty("user.dir") + File.separator + Constants.BASE_USER_FILE_PATH;
        String fileUrl = basePath + "/" + fileType + "/" + yyyyMMdd + "/" + filename;
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            HttpServletResponse response = HttpServletContextKit.getHttpServletResponse();
            ServletUtil.write(response, new FileInputStream(new File(fileUrl)), "image/" + suffix);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new KvfException("访问静态图片【" + fileUrl + "】出错：" + e.getMessage());
        }
    }

    /**
     * 使用流访问项目外部的静态文件
     */
    @GetMapping(value = "static/{uploadType}/{fileType}/{yyyyMMdd}/{filename}")
    public void staticFile(@PathVariable String uploadType, @PathVariable String fileType, @PathVariable String yyyyMMdd, @PathVariable String filename) {
        String basePath = System.getProperty("user.dir") + File.separator + Constants.BASE_USER_FILE_PATH;
        String fileUrl = basePath + "/" + uploadType + "/" + fileType + "/" + yyyyMMdd + "/" + filename;
        HttpServletResponse response = HttpServletContextKit.getHttpServletResponse();
        ServletUtil.write(response, new File(fileUrl));
    }

    @GetMapping(value = "search/user")
    public R searchUsers(String keyword) {
        return R.ok(userService.search(keyword));
    }

}
