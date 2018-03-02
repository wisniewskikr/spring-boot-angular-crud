package pl.kwi.springboot.configs;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Value(value = "${ldap.url}")
    private String ldapUrl;
	
	@Value(value = "${ldap.user}")
    private String ldapUser;
	
	@Value(value = "${ldap.password}")
    private String ldapPassword;
	
	@Value(value = "${ldap.trustStore.path}")
	private String ldapTrustStorePath;
	
	@Value(value = "${ldap.trustStore.password}")
	private String ldapTrustStorePassword;
	
	@Bean
	public LdapContext ldapContext() {	
		
		LdapContext ldapContext = null;
		
		handleTrustStore();
		
		try {
			
			Map<String, String> map = new HashMap<String, String>();	        
			map.put(Context.SECURITY_AUTHENTICATION, "simple");
			map.put(Context.PROVIDER_URL, ldapUrl);
			map.put(Context.SECURITY_PRINCIPAL, ldapUser);
			map.put(Context.SECURITY_CREDENTIALS, ldapPassword);
			map.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        
	        ldapContext = new InitialLdapContext(
	                new Hashtable<String, String>(map), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ldapContext;
		
	}
	
	private void handleTrustStore() {
		
		try {
			
			URL url = this.getClass().getClassLoader().getResource(ldapTrustStorePath);
			String path = url.toURI().getPath();
			System.setProperty("javax.net.ssl.trustStore", path);
			System.setProperty("javax.net.ssl.trustStorePassword", ldapTrustStorePassword);
			
		} catch (URISyntaxException e1) {
			System.err.println(e1.getMessage());
		}
		
	}

}
