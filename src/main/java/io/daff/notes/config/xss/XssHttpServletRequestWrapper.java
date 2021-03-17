package io.daff.notes.config.xss;

import cn.hutool.http.HtmlUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import io.daff.notes.util.JacksonUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daffupman
 * @since 2021/3/17
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            value = HtmlUtil.filter(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues != null && parameterValues.length > 0) {
            for (int i = 0; i < parameterValues.length; i++) {
                String value = parameterValues[i];
                if (!StringUtils.isEmpty(value)) {
                    value = HtmlUtil.filter(value);
                }
                parameterValues[i] = value;
            }
        }
        return parameterValues;
    }

    @Override
    public Map<String, String[]> getParameterMap() {

        LinkedHashMap<String, String[]> filteredMap = new LinkedHashMap<>();

        Map<String, String[]> parameterMap = super.getParameterMap();
        if (!CollectionUtils.isEmpty(parameterMap)) {
            for (String key : parameterMap.keySet()) {
                String[] values = parameterMap.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    values[i] = StringUtils.isEmpty(value) ? null : HtmlUtil.filter(value);
                }
                filteredMap.put(key, values);
            }
        }
        return filteredMap;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return StringUtils.isEmpty(value) ? null : HtmlUtil.filter(value);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        StringBuffer sb = new StringBuffer();
        try (
                ServletInputStream is = super.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> map = JacksonUtil.toBean(sb.toString(), new TypeReference<Map<String, Object>>(){});
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                if (!StringUtils.isEmpty(value)) {
                    resultMap.put(entry.getKey(), HtmlUtil.filter(value.toString()));
                }
            } else {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(JacksonUtil.fromBean(resultMap).getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
