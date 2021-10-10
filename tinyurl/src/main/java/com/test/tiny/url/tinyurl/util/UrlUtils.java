package com.test.tiny.url.tinyurl.util;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.test.tiny.url.tinyurl.common.exception.InternalServerErrorException;
import com.test.tiny.url.tinyurl.common.constants.APIConstants;

/**
 * @author prateek
 *
 */

public class UrlUtils {

	public static String getUriExceptPrefix(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String uri = getUriFromUrl(requestURL.toString()).replace(APIConstants.API_PREFIX, "");
		return uri;
	}

	private static String getUriFromUrl(String url) {
		String uriString = "";
		if (!StringUtils.isEmpty(url)) {
			url = url.trim();
		}
		try {
			Pattern pattern = Pattern.compile(APIConstants.URL_REGEX);
			Matcher matcher = pattern.matcher(url);
			while (matcher.find()) {
				uriString = matcher.group(4);
			}
			if (StringUtils.isEmpty(uriString)) {
				URI uri = new URI(url);
				uriString = uri.getPath();
				String query = uri.getQuery();
				if (query != null) {
					uriString = uriString + APIConstants.QUERY_SEPARATOR + query;
				}
			}
		} catch (Exception e) {
			System.out.println("Error while parsing url");// better to use log4j logs instead of system prints
			throw new InternalServerErrorException("Oops... Something went wrong, Please try after some time!");
		}
		return uriString;
	}

}
