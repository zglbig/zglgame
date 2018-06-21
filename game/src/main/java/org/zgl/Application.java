package org.zgl;

import org.zgl.net.CClientApplication;

/**
 * @作者： big
 * @创建时间： 2018/6/20
 * @文件描述：
 */
public class Application {
    private static CClientApplication cc;
    public static CClientApplication getCc() {
        return cc;
    }
    public static void main(String[] args) {
        cc = new CClientApplication();
        cc.run();//链接后注册
    }
}
