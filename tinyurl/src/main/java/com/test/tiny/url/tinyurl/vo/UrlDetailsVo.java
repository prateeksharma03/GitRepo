package com.test.tiny.url.tinyurl.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author prateek
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UrlDetailsVo extends ResponseWrapper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String tinyUrl;
	
	public UrlDetailsVo() {
		
	}
	
	public UrlDetailsVo(String tinyUrl) {
		this.tinyUrl = tinyUrl;
		this.setStatus("Success");
	}
	public String getTinyUrl() {
		return tinyUrl;
	}
	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}
	
	
	
	
	
	

}
