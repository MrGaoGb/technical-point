package com.technical.comm.utils;

import com.technical.comm.exception.CodeMessageRuntimeException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Mr.Gao
 * @date: 2021/9/8 15:02
 * @description:
 */
public class TxtDocReaderUtil {

    private static final Logger log = LoggerFactory.getLogger(TxtDocReaderUtil.class);
    private static final String RES_BUSINESS_CHECK_FAIL = "0104";    //业务校验
    private static final String STREAM_CLOSE_ERROR_CODE = "03010010";
    private static final Integer UPLOAD_NUM_LIMIT = 1000;

    private TxtDocReaderUtil() {
    }

    /**
     * 方法说明:从text文本(base64格式)中读取数据，一行一行的读，存储list中 最后返回
     * (操作号)
     *
     * @param terDoc
     * @return
     */
    public static List<String> ergodicTerminals(String terDoc) {
        log.info("遍历文件操作号开始..");
        BufferedReader reader = null;
        InputStreamReader streamRead = null;
        ByteArrayInputStream byteStream = null;
        List<String> listTerDoc = new ArrayList<>();
        try {
            byte[] data = Base64.getDecoder().decode(terDoc);
            byteStream = new ByteArrayInputStream(data);
            streamRead = new InputStreamReader(byteStream, "UTF-8");
            reader = new BufferedReader(streamRead);
            String lineTxt;
            String str1 = "";
            while ((lineTxt = reader.readLine()) != null) {
                String[] str = lineTxt.split(",");
                str1 = str[0].trim();
                //校验是否包含乱码
                if (isMessyCode(str1)) {
                    throw new CodeMessageRuntimeException(RES_BUSINESS_CHECK_FAIL, "上传数据存在乱码,请校验后再上传!");
                }
                if (StringUtils.isNotEmpty(str1) && !listTerDoc.contains(str1)) {
                    listTerDoc.add(str1);
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new CodeMessageRuntimeException(RES_BUSINESS_CHECK_FAIL, "字符编码转化失败");
        } catch (IOException e) {
            throw new CodeMessageRuntimeException(RES_BUSINESS_CHECK_FAIL, "流处理异常!");
        } finally {
            if (streamRead != null) {
                try {
                    streamRead.close();
                } catch (IOException e) {
                    throw new CodeMessageRuntimeException(STREAM_CLOSE_ERROR_CODE, "流关闭失败!");
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new CodeMessageRuntimeException(STREAM_CLOSE_ERROR_CODE, "流关闭失败!");
                }
            }
            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException e) {
                    throw new CodeMessageRuntimeException(STREAM_CLOSE_ERROR_CODE, "流关闭失败!");
                }
            }
        }
        log.info("文件上传识别的操作号数量为：{}条", listTerDoc.size());
        if (listTerDoc.size() > UPLOAD_NUM_LIMIT) {
            throw new CodeMessageRuntimeException(RES_BUSINESS_CHECK_FAIL, "一次性可操作数量不得超过1000条!");
        }
        if (CollectionUtils.isEmpty(listTerDoc)) {
            throw new CodeMessageRuntimeException(RES_BUSINESS_CHECK_FAIL, "上传的文件内容为空");
        }
        return listTerDoc;
    }


    /**
     * 判断字符串中是否包含乱码    true：包含乱码     false：不包含乱码
     *
     * @param strName
     * @return
     */
    public static boolean isMessyCode(String strName) {
        try {
            Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();

            int length = (ch != null) ? ch.length : 0;
            for (int i = 0; i < length; i++) {
                char c = ch[i];
                if (!Character.isLetterOrDigit(c)) {
                    String str = "" + ch[i];
                    if (!str.matches("[\u4e00-\u9fa5]+")) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
