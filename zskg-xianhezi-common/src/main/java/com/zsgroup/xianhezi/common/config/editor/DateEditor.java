package com.zsgroup.xianhezi.common.config.editor;


import com.zsgroup.xianhezi.common.utils.DateHelper;

import java.beans.PropertyEditorSupport;

/**
 * @author zhangxd
 */
public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(DateHelper.parseDate(text));
    }

}
