package com.test.tiny.url.tinyurl.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;


/**
 * @author prateek
 *
 */


public class UrlDetailsDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "Url is mandatory")
	private String url;
	private String clientCode;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	
	

}
