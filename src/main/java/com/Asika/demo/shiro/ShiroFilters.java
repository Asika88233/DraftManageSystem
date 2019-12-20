package com.Asika.demo.shiro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.Asika.demo.domain.pojo.ShiroFilterPojo;
import com.Asika.demo.util.YamlConfigFactory;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Component
@PropertySource(value = "classpath:/config/shirofilter.yml",factory = YamlConfigFactory.class)
@ConfigurationProperties(prefix = "filter")
public class ShiroFilters {
  private String loginUrl;
  private ArrayList<ShiroFilterPojo>filterlist;
  public Map<String, String> getFilter() {
	  Map<String, String> map=new LinkedHashMap<String, String>();
	  for (ShiroFilterPojo shiroFilterPojo : this.getFilterlist()) {
		map.put(shiroFilterPojo.getUrl(), shiroFilterPojo.getAuth());
	}
	  return map;
}
}
