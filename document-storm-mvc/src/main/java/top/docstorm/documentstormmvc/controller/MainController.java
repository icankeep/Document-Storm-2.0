package top.docstorm.documentstormmvc.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.docstorm.documentstormcommon.domain.FileInfo;
import top.docstorm.documentstormcommon.service.FileInfoService;
import top.docstorm.documentstormcommon.service.MessageService;
import top.docstorm.documentstormcommon.utils.FastJsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/15
 */
@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private MessageService messageService;

    @Value("${uploadBasePath}")
    private String basePath;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/fileList")
    public String fileList(Model model) {
        return "fileList";
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("opt") String opt, Model model) {
        model.addAttribute("opt", opt);
        return "fileUpload";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping("/signIn")
    public String signIn(Model model) {
        return "signIn";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("opt") String opt,
                                   @RequestParam("newFileName") String newFileName, Model model, HttpServletRequest request){
        try {
            // 获取文件名
            String fileName = fileInfoService.generateUploadFileName(uploadFile.getOriginalFilename(), newFileName);
            // 重新生成文件名
            String fileKey = UUID.randomUUID().toString();
            String saveFileName = basePath + fileInfoService.generateSaveFileName(fileKey, opt);
            // 保存文件
            uploadFile.transferTo(new File(saveFileName));
            // 调用服务保存文件信息
            FileInfo fileInfo = fileInfoService.insertFromUploadInfo(fileName, fileKey, opt, null);
            String fileInfoString = JSON.toJSONString(fileInfo);
            LOGGER.info("已上传文件：" + fileInfoString);
            // 发送文件待转换消息
            messageService.sendTransFileTopicMessage(fileInfoString);
            return "fileList";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }
}
