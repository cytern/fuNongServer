package com.funong.funong.controller;

import com.funong.funong.mapper.ImgDao;
import com.funong.funong.plugin.ChangeDate;
import com.funong.funong.pojo.Img;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@RestController
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private ImgDao imgDao;
    ChangeDate changeDate = new ChangeDate();
    @GetMapping("testFile")
    public void getFile(){

    }

    @RequestMapping("everyone/simpleUpload")
    public HashMap<Object,Object> uploadSimple(@RequestParam("file") CommonsMultipartFile file , HttpServletRequest request) throws IOException {
        String path = "F:\\Imgs\\";
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
            logger.debug("创建空的路径");
        }
        file.transferTo(new File(realPath+"/"+file.getOriginalFilename()));
       Img img = new Img();
        img.setImgUrl(file.getOriginalFilename()+file.hashCode());
        img.setImgStatus("wait");
        img.setCreateTime(changeDate.getDate());
        imgDao.insertSelective(img);
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("fileUrl",img.getImgUrl());
        return hashMap;
    }

    @RequestMapping("everyone/downLoad/{fileName:.+}")
    public String downLoads(HttpServletResponse response, HttpServletRequest request, @PathVariable("fileName") String fileName) throws IOException {
        String path = "F:\\Imgs\\";
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        File file =new File(path,fileName);
        InputStream input = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int index =0;
        while ((index=input.read(buff))!=-1){
            out.write(buff,0,index);
            out.flush();
        }
        out.close();
        input.close();
        return null;

    }
    @GetMapping("manager/getAllImgs")
    public HashMap<Object,Object> getAllImgs(){
        List<Img> imgs = imgDao.getAllImg();
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("img",imgs);
        return hashMap;
    }

}
