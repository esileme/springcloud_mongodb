package com.zsgroup.xianhezi.api.controller;

import com.zsgroup.xianhezi.common.config.editor.DateEditor;
import com.zsgroup.xianhezi.common.config.editor.StringEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class BaseController {
	private final static Logger log = LoggerFactory.getLogger(BaseController.class);

	protected String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null)
			ip = request.getRemoteAddr();
		return ip;
	}

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     *
     * @param binder the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEditor());
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

	/*@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
	}*/

}
