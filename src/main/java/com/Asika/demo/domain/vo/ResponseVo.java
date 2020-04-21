package com.Asika.demo.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo {
   private static final String SUCCESS_CODE="200"; 
   private static final String ERROR_CODE="500"; 
   private String code;
   private String msg;
   private Object data;
   public static ResponseVo success() {
	   ResponseVo result= new ResponseVo();
	   result.setCode(SUCCESS_CODE);
	   return result;
   }
   public static ResponseVo error() {
	   ResponseVo result= new ResponseVo();
	   result.setCode(ERROR_CODE);
	   return result;
   }
}
