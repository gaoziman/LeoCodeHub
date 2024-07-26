package org.leocoder.codehub.admin.service;

import org.leocoder.codehub.common.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-26 15:46
 * @description :
 */
public interface AdminFileService {
    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @return Result
     */
    Result uploadFile(MultipartFile file);
}
