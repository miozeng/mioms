package com.mioms.core.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mioms.core.dto.UserGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/group", produces = {"application/json;charset=UTF-8"})
@Api(value = "/group", description = "group's Operation")
public class GroupController {

	@RequestMapping(value = "addGroup", method = RequestMethod.PUT)
    @ApiOperation(notes = "addGroup", httpMethod = "POST", value = "add a group")
   // @ApiResponses(value = {@ApiResponse(code = 405, message = "invalid input")})
    public UserGroup addGroup(@ApiParam(required = true, value = "group data") @RequestBody UserGroup group) {
        return group;
    }

    @RequestMapping(value = "getAccessibleGroups", method = RequestMethod.GET)
    @ApiOperation(notes = "getAccessibleGroups", httpMethod = "GET", value = "get my list")
    public List<UserGroup> getAccessibleGroups() {
    	UserGroup group1 = new UserGroup();
        group1.setGroupId("1");
        group1.setName("testGroup1");

        UserGroup group2 = new UserGroup();
        group2.setGroupId("2");
        group2.setName("testGroup2");

        List<UserGroup> groupList = new LinkedList<UserGroup>();
        groupList.add(group1);
        groupList.add(group2);

        return groupList;
    }
}
