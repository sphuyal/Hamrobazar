package com.sandesh.hamrobazar;

import com.sandesh.hamrobazar.BLL.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginCheck {

    @Test
    public void checkLogin(){
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkUser("n@gmail.com","hello123");
        assertEquals(true,result);
    }
}
