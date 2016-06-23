package me.code41.seed.web.controller.interceptors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CheckRequestParamInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String body = request.getParameter("body");
        if (StringUtils.isNotEmpty(body)) {
            String clientOs = request.getParameter("platCode");
            request.setAttribute("requestBody", body);
            request.setAttribute("clientOs", clientOs);
            return true;
        } else {
            String requestBody = getPostData(request);
            String apiVersion = request.getParameter("apiVersion");
            String clientOs = request.getParameter("clientOs");
            String clientVersion = request.getParameter("clientVersion");
            String signKey = request.getParameter("signKey");
            String channel = request.getParameter("channel");

            // temp
            if (channel == null) {
                channel = "Xiaomi";
            }

            if (!checkParamIgnoreIllegalParam(apiVersion, clientOs, clientVersion, channel, signKey, requestBody)) {
                //        	throw new O2OException(BaseResponseCode.PARAM_ERROR);
                return false;
            }
            request.setAttribute("requestBody", requestBody);
            return true;
        }
    }

    /**
     * 获取post请求的body体
     *
     * @param request
     * @return
     */
    private String getPostData(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = sb.toString();
        return result;
    }

//    /**
//     * 获取post请求的body体
//     * @param request
//     * @return
//     */
//    private String getPostData_bak(HttpServletRequest request){
//		StringBuffer sb = new StringBuffer() ;
//		String result = null;
//		try{
//			InputStream is = request.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String s = "" ;
//			while((s=br.readLine())!=null){
//				sb.append("\n").append(s);
//			}
//			result =sb.toString();
//			result = result.substring(1);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		logger.warn("post body:{}", result);
//		return result;
//	}

    /**
     * 检查参数（不检测非法符号）
     * 暂时只对saveCrash使用
     *
     * @param apiVersion
     * @param clientOs
     * @param clientVersion
     * @param channel
     * @param signKey
     * @param requestBody
     * @return useCode clientCode version 全部非空返回true，否则false
     */
    public boolean checkParamIgnoreIllegalParam(String apiVersion, String clientOs, String clientVersion, String channel,
                                                String signKey, String requestBody) {
//		logger.debug("apiVersion=" + apiVersion + "  ; clientOs=" + clientOs + "  ; clientVersion=" + clientVersion);

//        if (StringUtils.isBlank(apiVersion)
//                || StringUtils.isBlank(clientOs)
//                || StringUtils.isBlank(clientVersion)
//                || StringUtils.isBlank(channel)) {
//            return false;
//        }
//
//        //md5加密监测
//        String md5Config = Md5Config.getProperty("IS_MD5_OPEN");
//        if (null != md5Config && "true".equalsIgnoreCase(md5Config) && StringUtils.isNotBlank(requestBody)) {
//            String md5Key = Md5Config.getProperty("MD5_" + clientOs.toUpperCase(), "default_md5_key");
//
//            String md5Local = MD5Encrypt.encrypt(requestBody + md5Key);
//			logger.debug("*************************requestBody={}, md5Key={}", requestBody, md5Key);
//			logger.debug("================MD5 check local={},  request={}", md5Local, signKey);

//            if (!md5Local.equalsIgnoreCase(signKey)) {
//				logger.warn("MD5 check warn: local=" + md5Local + "；request=" + signKey
//						+ "；apiVersion=" + apiVersion
//						+ "  ; clientOs=" + clientOs
//						+ "  ; clientVersion=" + clientVersion);
//				logger.warn("request body:{}", requestBody);
                // clientOs=MOCK 是测试用的
//                if ("MOCK".equals(clientOs)) {
//                    return true;
//                }
//                return false;
//            }
//        }

        return true;
    }
}
