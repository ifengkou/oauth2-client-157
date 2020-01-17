package auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@SpringBootApplication
@RequestMapping("/")
@RestController
@Slf4j
public class Oauth2Client157Application {

	@RequestMapping("/print_request")
		public Map<String,List> printRequest(HttpServletRequest request) throws JsonProcessingException {

		List<String> headerInfo = new ArrayList<>();
		//2、获得所有的头的名称
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			headerInfo.add(headerName+"::"+headerValue);
		}
		List<String> urlInfo = new ArrayList<>();

		String method = request.getMethod();
		urlInfo.add("method:" + method);
		// 2、获得请求的资源相关的内容
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		urlInfo.add("uri:" + requestURI);
		urlInfo.add("url:" + requestURL);

		// 获得web应用的名称
		String contextPath = request.getContextPath();
		urlInfo.add("context_path：" + contextPath);
		// 地址后的参数的字符串
		String queryString = request.getQueryString();
		//urlInfo.add(queryString);
		// 3、获得客户机的信息---获得访问者IP地址
		String remoteAddr = request.getRemoteAddr();
		urlInfo.add("IP:" + remoteAddr);
		urlInfo.add("ServerPort:" + request.getServerPort());
		urlInfo.add("LocalPort:" + request.getLocalPort());
		urlInfo.add("RemotePort:" + request.getRemotePort());


		urlInfo.add("serverName:"+request.getServerName());
		urlInfo.add("schema:"+request.getScheme());

		Map<String,List> returnMap = new HashMap<>(2);
		returnMap.put("header",headerInfo);
		returnMap.put("info",urlInfo);
		return returnMap;
	}

	@RequestMapping("/user_name")
	public Principal user2(Principal principal) {
		return principal;
	}

	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;

	}


	@GetMapping("/user_attr")
	public Map<String, Object> userinfo(Principal principal) {
		/*DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
		user.getAuthorities();
		Map<String, Object> details = user.getAttributes();*/

		OAuth2Authentication auth2Authentication = (OAuth2Authentication) principal;
		Map<String, Object> details = (Map<String, Object>)auth2Authentication.getUserAuthentication().getDetails();
		return details;
	}



	public static void main(String[] args) {
		SpringApplication.run(Oauth2Client157Application.class, args);
	}

}
