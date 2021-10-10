package com.test.tiny.url.tinyurl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.tiny.url.tinyurl.model.UrlDetails;

/**
 * @author prateek
 *
 */
public interface UrlRepository extends JpaRepository<UrlDetails, Long> {

	public static final String FIND_URL_DETAILS = "select * FROM url_details where url = ?1";
	public static final String FIND_URL_BY_TINY = "select * FROM url_details where tiny_url = ?1";

	/**
	 * Function to fetch url details for given actual url
	 * @param url
	 * @return List of UrlDetails
	 */
	@Query(value = FIND_URL_DETAILS, nativeQuery = true)
	public List<UrlDetails> findUrl(String url);
	
	
	/**
	 * Method to get url details by tiny url
	 * 
	 * @param tinyUrl
	 * @return List of UrlDetails
	 */
	@Query(value = FIND_URL_BY_TINY, nativeQuery = true)
	public List<UrlDetails> findUrlDetailsByTinyUrl(String tinyUrl);
}
