package com.test.tiny.url.tinyurl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.tiny.url.tinyurl.dto.UrlDetailsDto;
import com.test.tiny.url.tinyurl.service.UrlService;
import com.test.tiny.url.tinyurl.vo.ResponseWrapper;
import com.test.tiny.url.tinyurl.vo.UrlDetailsVo;

/**
 * @author prateek
 * 
 * Controller class
 *
 */

@RestController
@RequestMapping("/url")
public class TinyUrlController {

	@Autowired
	private UrlService service;

	/**
	 * Controller function to generate tiny url
	 * @param urlDetailsDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<ResponseWrapper> getTinyUrl(@RequestBody UrlDetailsDto urlDetailsDto) throws Exception {
		System.out.println("<<<<<<<<<<<>>>>>>>>>>>>> received url : " + urlDetailsDto.getUrl());// add log4j
		UrlDetailsVo urlVo = service.createTinyUrl(urlDetailsDto);
		return new ResponseEntity<>(urlVo, HttpStatus.OK);
	}

	/**
	 * Controller function to redirect tiny urls to actual urls
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/*")
	public ResponseEntity<ResponseWrapper> redirectToLongUrl(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		response.setHeader("Location", service.processRedirect(request));
		return new ResponseEntity<>(new UrlDetailsVo(), HttpStatus.FOUND);
	}

}
