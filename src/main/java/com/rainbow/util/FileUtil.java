package com.rainbow.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class FileUtil {
    private static final String SFTP_HOST = "120.46.13.61";
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "root"; // TODO: 替换为实际用户名
    private static final String SFTP_PASS = "ZhaoX19990227"; // TODO: 替换为实际密码
    private static final String SFTP_DIR = "/usr/images";

    public static String uploadFile(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + extension;

        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
        session.setPassword(SFTP_PASS);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
        sftp.connect();

        try (InputStream inputStream = file.getInputStream()) {
            sftp.cd(SFTP_DIR);
            sftp.put(inputStream, newFilename);
        } finally {
            sftp.disconnect();
            session.disconnect();
        }

        return "http://" + SFTP_HOST + "/images/" + newFilename;
    }
} 