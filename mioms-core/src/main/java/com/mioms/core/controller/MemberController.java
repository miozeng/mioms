package com.mioms.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/member", produces = { "application/json;charset=UTF-8" })
@Api(value = "/member", description = "member API")
public class MemberController {

	@ApiOperation(notes = "This function get the memebr by Id", httpMethod = "GET", value = "get group by id")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "Member ID", required = true, dataType = "long", paramType = "query") })
	@RequestMapping(value = "getMemberById", method = RequestMethod.GET)
	@ResponseBody
	public String getMemberById(@RequestParam(value = "id", required = true) Long id) {
		System.out.println("-------- get member by id -------" + id);

		return "";
	}

}
