package com.mios.ws.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.junit.Before;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextHierarchy({  
    @ContextConfiguration(name = "parent", locations = "classpath:config/application-config-hibernate.xml"),  
    @ContextConfiguration(name = "child", locations = "classpath:mvc-config.xml")
}) 
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;
	
	protected MockMvc mockMvc;
	
	@Rule
	public final JUnitRestDocumentation  restDocumentation = new JUnitRestDocumentation("target/dev/generated-snippets");
	 
	protected RestDocumentationResultHandler documentationHandler; 
	
	@Before
	public void setup() {
		
		this.documentationHandler = document("{method-name}",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()));
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();
	}

}
