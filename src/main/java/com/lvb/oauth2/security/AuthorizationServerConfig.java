package com.lvb.oauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;

	static final String CLIEN_ID = "vinodh";
	static final String CLIENT_SECRET = "password";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("vinodh");
        return converter;
	}

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(CLIEN_ID)
				.secret(new BCryptPasswordEncoder().encode(CLIENT_SECRET))
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	 @Bean
	    public WebResponseExceptionTranslator loggingExceptionTranslator() {
	        return new DefaultWebResponseExceptionTranslator() {
	            @Override
	            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
	                // This is the line that prints the stack trace to the log. You can customise this to format the trace etc if you like
	                e.printStackTrace();

	                // Carry on handling the exception
	                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
	                HttpHeaders headers = new HttpHeaders();
	                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
	                OAuth2Exception excBody = responseEntity.getBody();
	                return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
	            }
	        };
	    }
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.
		          pathMapping("/oauth/token", "/authorize/jwt")
		         .exceptionTranslator(loggingExceptionTranslator())
		         .tokenStore(tokenStore())
		         .authenticationManager(authenticationManager)
		         .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter());
	}
}