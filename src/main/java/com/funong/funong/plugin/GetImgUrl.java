package com.funong.funong.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: cytern
 * @Date: 2020/5/24 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetImgUrl {
    private String baseUrl;
    private String linuxBaseIp="http://62.234.29.109:2020/";
    private String windowsBaseIp="http://localhost:2020/";
    private String downloadUrl = "everyone/downLoad/";
    private String linuxStorage="/export/data/img";
    private String windowsStorage="F:\\Imgs\\";
    private String export;
    private String storage;

    public String getBaseUrl(){
        this.baseUrl = System.getProperty("os.name");

        if (this.baseUrl.equals("Windows")){
           this.export = windowsBaseIp + downloadUrl;
           this.storage = windowsStorage;
        }else {
          this.export = linuxBaseIp + downloadUrl;
          this.storage = linuxStorage;
        }
        return null;
    }
}
