package com.mioms.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.codec.Base64;

public class LdapHandlerUtil {

	private static final Logger logger = LogManager.getLogger(LdapHandlerUtil.class);
	private static String url;
	private static String dn;
	private static String dpassword;
	private static String paswdAtr;
	private static String factory;
	private static String searchBase;
	private static String searchFilter;
	
	
	private static DirContext ctx;

	
//	public LdapHandlerUtil() {
//		super();
//	}


	public static String getUrl() {
		return url;
	}

	//	public LdapHandlerUtil(String url, String dn, String dpassword, String paswdAtr, String factory,String searchBase, String searchFilter) {
//		super();
//		this.url = url;
//		this.dn = dn;
//		this.dpassword = dpassword;
//		this.paswdAtr = paswdAtr;
//		this.factory = factory;
//		this.searchBase = searchBase;
//		this.searchFilter = searchFilter;
//	}
	public static  void innit(String purl, String pdn, String pdpassword, String ppaswdAtr, String pfactory,String psearchBase, String psearchFilter) {
		url = purl;
		dn = pdn;
		dpassword = pdpassword;
		paswdAtr = ppaswdAtr;
		factory = pfactory;
		searchBase = psearchBase;
		searchFilter = psearchFilter;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DirContext getCtx() {
		String account = dn; // binddn
		String password = dpassword; // bindpwd
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, factory);
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, account);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			// connect ldap
			ctx = new InitialDirContext(env);
			logger.info("authenticate ldap success");
		} catch (javax.naming.AuthenticationException e) {
			logger.error("authenticate ldap failure",e);
		} catch (Exception e) {
			logger.error("authenticate ldaperror：",e);
		}
		return ctx;
	}

	public static boolean verifySHA(String ldappw, String inputpw) throws NoSuchAlgorithmException {

		// MessageDigest provide arithmetic like MD5  or  SHA and LDAP used SHA-1
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		// get encrypted char 
		if (ldappw.startsWith("{SSHA}")) {
			ldappw = ldappw.substring(6);
		} else if (ldappw.startsWith("{SHA}")) {
			ldappw = ldappw.substring(5);
		}

		// Decode BASE64
		byte[] ldappwbyte = Base64.decode(ldappw.getBytes());
		byte[] shacode;
		byte[] salt;

		//  top 20 is encrypts for SHA-1;
		if (ldappwbyte.length <= 20) {
			shacode = ldappwbyte;
			salt = new byte[0];
		} else {
			shacode = new byte[20];
			salt = new byte[ldappwbyte.length - 20];
			System.arraycopy(ldappwbyte, 0, shacode, 0, 20);
			System.arraycopy(ldappwbyte, 20, salt, 0, salt.length);
		}

		md.update(inputpw.getBytes());
		md.update(salt);
		
		byte[] inputpwbyte = md.digest();

		return MessageDigest.isEqual(shacode, inputpwbyte);
	}

	@SuppressWarnings("rawtypes")
	public static  boolean authenticate(String usr, String pwd) {
		boolean success = false;
		DirContext ctx = null;
		try {
			ctx = getCtx();
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String filterStr = MessageFormat.format(searchFilter, usr);
			NamingEnumeration en = ctx.search(searchBase, filterStr, constraints);
			// find all user
			while (en != null && en.hasMoreElements()) {
				Object obj = en.nextElement();
				if (obj instanceof SearchResult) {
					SearchResult si = (SearchResult) obj;
					logger.info("name:   " + si.getName());
					Attributes attrs = si.getAttributes();
					if (attrs == null) {
						logger.info("No   attributes");
					} else {
						Attribute attr = attrs.get(paswdAtr);
						Object o = attr.get();
						byte[] s = (byte[]) o;
						String pwd2 = new String(s);
						success = verifySHA(pwd2, pwd);
						logger.info("authenticate user success");
						return success;
					}
				} else {
					logger.info(obj);
				}
			}
			ctx.close();
		} catch (NoSuchAlgorithmException ex) {
			try {
				if (ctx != null) {
					ctx.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}

		} catch (NamingException ex) {
			try {
				if (ctx != null) {
					ctx.close();
				}
			} catch (NamingException namingException) {
				namingException.printStackTrace();
			}

		}
		return false;
	}

}
