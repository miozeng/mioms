package com.mios.ws.controller;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mioms.core.util.LdapHandlerUtil;



//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(LdapHandlerUtil.class)
@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", "com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*"})
public class UserControllerTest extends AbstractContextControllerTests{

	/*private MockMvc mockMvc;

	@Rule
	public final JUnitRestDocumentation  restDocumentation = new JUnitRestDocumentation("target/dev/generated-snippets");
	
	private RestDocumentationResultHandler documentationHandler; 
	@Before
	public void setup() {
		this.documentationHandler = document("{method-name}",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()));
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();
	}*/
	
	@Test  
	public void testUserLogin() throws Exception {  
		// Given
		mockStatic(LdapHandlerUtil.class);
		expect(LdapHandlerUtil.getUrl()).andReturn("aa").anyTimes();
//	    LdapHandlerUtil.innit(EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString(),
//	    		EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString());
		expect(LdapHandlerUtil.authenticate("mio", "zm123456")).andReturn(true).anyTimes();
		replay(LdapHandlerUtil.class);
		
		String requestBody = "{\"name\":\"mio\", \"password\":\"zm123456\"}";  
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")   
				    .contentType(MediaType.APPLICATION_JSON)
		            .content(requestBody)  
		            .accept(MediaType.APPLICATION_JSON)) 
				    .andExpect(content().string("{\"uid\":\"382\",\"status\":\"Y\"}"))
				    .andExpect(jsonPath("$.uid").value("382"))
				    .andDo(document("{method-name}",requestFields(
					        fieldWithPath("name").description("The user's name"), 
					        fieldWithPath("password") .description("The user's password")),
							responseFields(fieldWithPath("uid").description("userid"),
									fieldWithPath("status").description("handle status "))));
	}
	
	/*
	@Test  
	public void testUserLogin() throws Exception {  
		String requestBody = "{\"name\":\"mio\", \"password\":\"zm123456\"}";  
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")   
				    .contentType(MediaType.APPLICATION_JSON)
		            .content(requestBody)  
		            .accept(MediaType.APPLICATION_JSON)) 
				    .andExpect(content().string("{\"uid\":\"382\",\"status\":\"Y\"}"))
				    .andExpect(jsonPath("$.uid").value("382"))
				    .andDo(this.documentationHandler.document(
							responseFields(fieldWithPath("uid").description("userid"),
									fieldWithPath("status").description("handle status "))));
	}*/
	
	@Test  
	public void testView() throws Exception {  
	
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/findAll"))  
//		            .andExpect(MockMvcResultMatchers.view().name("user/view"))  
//		            .andExpect(MockMvcResultMatchers.model().attributeExists("user"))  
		            .andDo(MockMvcResultHandlers.print())  
		            .andReturn();  
		 System.out.println(result);
	}
	
	@Test  
	public void testAdd() throws Exception {  
		String requestBody = "{\"age\":\"20\", \"name\":\"mio\"}";  
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/save")  
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(requestBody)  
		            .accept(MediaType.APPLICATION_JSON)) 
				 .andDo(MockMvcResultHandlers.print()) 
				 .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				 .andExpect(jsonPath("$.ret").value(true))
				 .andReturn();   //执行请求  
//		        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) ;//验证响应contentType  
//		        .andExpect(jsonPath("$.ret").value(true)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/  
		 
		 System.out.println(result.getResponse().getContentAsString());
	}
}
