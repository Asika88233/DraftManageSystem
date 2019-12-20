package com.Asika.demo.domain.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**|
 *      由于yml的key无法解析url中的斜杠，只有专门写个类来解析
 * @author Asika
 *
 */
public class ShiroFilterPojo {
       private String url;
       private String auth;
}
