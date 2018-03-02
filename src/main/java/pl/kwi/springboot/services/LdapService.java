package pl.kwi.springboot.services;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.kwi.springboot.entities.UserEntity;

@Service
public class LdapService {
	
	@Value(value = "${ldap.dn}")
    private String ldapDn;
	
	@Autowired
	private LdapContext ldapContext;
	
	public void createUser(UserEntity user) {	
		
        // entry's DN 
		String entryDN = String.format("cn=%s,", user.getCn()) + ldapDn;  
	
	    // entry's attributes  	
		Attribute cn = new BasicAttribute("cn", user.getCn()); 
		Attribute name = new BasicAttribute("name", user.getName()); 
		Attribute givenName = new BasicAttribute("givenName", user.getName());
	    Attribute oc = new BasicAttribute("objectClass");  
	    oc.add("top");  
	    oc.add("person");  
	    oc.add("organizationalPerson"); 
	    oc.add("user"); 
	    oc.add("inetOrgPerson");  
	
	    try {  

	    	BasicAttributes entry = new BasicAttributes();  
	    	entry.put(cn);
	    	entry.put(name); 
	    	entry.put(givenName);
	        entry.put(oc);  
	        ldapContext.createSubcontext(entryDN, entry);  
	
	    } catch (NamingException e) {  
	        System.err.println("create: error adding entry." + e);  
	    }
	    
	}
	
	public UserEntity readUser(String cn){
		
		UserEntity user = null;
		
		//filter
		String filter = String.format("(cn=%s)", cn);
		
		// search controls
		SearchControls sc = new SearchControls();
	    String[] attributeFilter = { "givenName" };
	    sc.setReturningAttributes(attributeFilter);
	    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
	    
	    try {
			NamingEnumeration<SearchResult> results = ldapContext.search(ldapDn, filter, sc);
			String name = null;
			while (results.hasMore()) {
			      SearchResult sr = results.next();
			      Attributes attrs = sr.getAttributes();
			      name = (String)attrs.get("givenName").get();
			      user = new UserEntity(cn, name);
			    }
		} catch (NamingException e) {
			System.err.println("read: error reading entry." + e);
		}
	    
	    return user;
	
	}
	
	public void updateUser(UserEntity user) {	
		
		String entryDN = String.format("cn=%s,", user.getCn()) + ldapDn; 
		
		try {

			ModificationItem[] mods = new ModificationItem[1];
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				    new BasicAttribute("givenName", user.getName()));
			
			ldapContext.modifyAttributes(entryDN, mods);
			
		} catch (NamingException e) {
			System.err.println("update: error updating entry." + e);
		}
		
	}
	
	public void deleteUser(String cn){
		
		String entryDN = String.format("cn=%s,", cn) + ldapDn; 
		
		try {
			ldapContext.destroySubcontext(entryDN);
		} catch (NamingException e) {
			System.err.println("delete: error deleting entry." + e);
		}
		
	}
	
	public List<UserEntity> getUserList(){
		
		List<UserEntity>  result = new ArrayList<UserEntity>();
		String name;
		String cn;
		
		//filter
		String filter = "(&(cn=*)(objectClass=user))";
		
		// search controls
		SearchControls sc = new SearchControls();
	    String[] attributeFilter = { "cn", "givenName" };
	    sc.setReturningAttributes(attributeFilter);
	    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
	    
	    try {
			NamingEnumeration<SearchResult> results = ldapContext.search(ldapDn, filter, sc);
			while (results.hasMore()) {
			      SearchResult sr = results.next();
			      Attributes attrs = sr.getAttributes();
			      cn = (String)attrs.get("cn").get();
//			      name = (String)attrs.get("givenName").get();			      
			      result.add(new UserEntity(cn, cn));
			    }
		} catch (NamingException e) {
			System.err.println("load: error reading entry." + e);
		}
	    
	    return result;
	
	}
	
	public String generateCn() {
		List<UserEntity> users = getUserList();
		if (users.isEmpty()) {
			return "testuser1";
		}
		
		UserEntity user = users.get(users.size() - 1);
		if(!user.getCn().contains("testuser")) {
			return "testuser1";
		}
		
		String currentCn = user.getCn();
		int index = Integer.valueOf(currentCn.substring("testuser".length(), currentCn.length()));
		index++;
		return "testuser" + index;
	}

}
