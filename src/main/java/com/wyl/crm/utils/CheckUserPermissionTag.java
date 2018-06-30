/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package com.wyl.crm.utils;

import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

/**
 *系统权限检查
 *
 * @author WangYiLin
 */

public class CheckUserPermissionTag extends ConditionalTagSupport {

    //*********************************************************************
    // Constructor and lifecycle management

    // initialize inherited and local state
    public CheckUserPermissionTag() {
        super();
        System.out.println("CheckUserPermissionTag初始化.................");
    }

    // Releases any resources we may have (or inherit)
    public void release() {
        super.release();
        System.out.println("CheckUserPermissionTag释放资源.................");
    }


    //*********************************************************************
    // Supplied conditional logic

    protected boolean condition() {
        System.out.println("CheckUserPermissionTag做条件判断.................");
        return UserContext.checkUserPermissionByPermissionName(permissionName);
    }


    //*********************************************************************
    // Private state

    private String permissionName;               // the value of the 'test' attribute


    //*********************************************************************
    // Accessors

    // receives the tag's 'test' attribute
    public void setPermissionName(String permissionName) {
        System.out.println("CheckUserPermissionTag设置参数.................permissionName:"+permissionName);
        this.permissionName = permissionName;
    }


    //*********************************************************************
    // Private utility methods

}
