package com.test.tiny.url.tinyurl.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.tiny.url.tinyurl.common.exception.BadRequestException;
import com.test.tiny.url.tinyurl.common.exception.InternalServerErrorException;
import com.test.tiny.url.tinyurl.common.exception.PreconditionFailedException;
import com.test.tiny.url.tinyurl.common.constants.APIConstants;
import com.test.tiny.url.tinyurl.dto.UrlDetailsDto;
import com.test.tiny.url.tinyurl.model.UrlDetails;
import com.test.tiny.url.tinyurl.repository.UrlRepository;
import com.test.tiny.url.tinyurl.util.UrlUtils;
import com.test.tiny.url.tinyurl.vo.UrlDetailsVo;

/**
 * @author prateek
 * Service class for business logic and to call repository/dao methods
 */

@Component
public class UrlService {
	
	@Autowired
	private UrlRepository repository;

	/**
	 * Function to generate tiny url if not already added
	 * 
	 * @param urlDetailsDto
	 * @return UrlDetailsVo
	 * @throws Exception
	 */
	public UrlDetailsVo createTinyUrl(UrlDetailsDto urlDetailsDto) throws Exception{
		if(urlDetailsDto == null || StringUtils.isEmpty(urlDetailsDto.getUrl()) ) {
			throw new BadRequestException("Invalid request, please check url value!");//add error log as well
		}
		String targetUrl = urlDetailsDto.getUrl();
		List<UrlDetails> dbUrls = repository.findUrl(targetUrl);
		if(dbUrls.size() > 0) {
			UrlDetails dbUrl = dbUrls.get(0);
			return new UrlDetailsVo(dbUrl.getTinyUrl());
		}
		String clientCode = urlDetailsDto.getClientCode();
		String suffix = RandomStringUtils.randomAlphanumeric(10);
		UrlDetails urlModel = new UrlDetails();
		urlModel.setClientCode((StringUtils.isEmpty(clientCode)) ? "0" : clientCode);
		urlModel.setTinyUrl(APIConstants.API_HOST+APIConstants.API_PREFIX+suffix);
		urlModel.setUrl(targetUrl);
		repository.save(urlModel);
		System.out.println(">>>> ABC >>>"+repository.findUrl(targetUrl).get(0).getId());
		return new UrlDetailsVo(urlModel.getTinyUrl());
	}
	
	
	/**
	 * Function to manage tiny url redirection 
	 * 
	 * @param request
	 * @return actual url
	 * @throws Exception
	 */
	public String processRedirect(HttpServletRequest request) throws Exception{
		System.out.println(":::::::::::"+UrlUtils.getUriExceptPrefix(request));
		String tinyUrl = APIConstants.API_HOST+APIConstants.API_PREFIX+UrlUtils.getUriExceptPrefix(request);
		List<UrlDetails> dbUrls = repository.findUrlDetailsByTinyUrl(tinyUrl);
		
		if(dbUrls.size() == 0) {
			throw new PreconditionFailedException("Sorry! given url doesn't exist.");// log error message as well
		}
		
		UrlDetails dbUrl = dbUrls.get(0);
		if(dbUrl == null || StringUtils.isEmpty(dbUrl.getUrl())) {
			throw new InternalServerErrorException("oops... Something went wrong, Please try again.");//log error message as well
		}
		return dbUrl.getUrl();
	}
}
