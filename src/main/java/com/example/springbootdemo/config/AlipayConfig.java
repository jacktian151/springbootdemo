package com.example.springbootdemo.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116662451";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvuBycE0/n++9UdE0rZfNxpm554vJxjFID5Ehn8R2gpy2v23p9LaHzi6A+EmnAWk2d2VBzPHS/O7Nok1sZ8rfTcPLxypFkaROaNwoTiHbWG7VPd3cyUADV+1gibu1/t/L6xaqF0zOHuCLPaOwoMFa9w0LYfH/LVxwPbevyfH5Zq97igufegxdd4R6elfwX8Rxi+PCr+WDa/b3ploFd7YarQLIxdXlB+Rk042OUFJfNrI0QD03OynmRvfncMrEcZtbDa0yD2A04+YtKsbexB0s/z0m1vTprxNr3v5r8ldgw3D5k1nySerW6BoJbJR9tPl6/7knipsAGpcGeEJkU0k9PAgMBAAECggEAFLnQlhApEqHCnuGA4KeLyVHqIfJEymPUoKTibXAhIHvXhbCLiAxD1+gWJHwjgQ6lrQKe3Ji65Ht0M9d9nVflmsjtvts3vvIt37iyNY+ZBLQL9K2Qn5yUHKkiwhz3YmMeA7og3NwGbfGoGXK0uCDLemJ3lfVJVYCWzLMYa2eHt6e98UPDSY4/9yH05GQFITTeBukz+NLKDqqHWgqaFR9gHPiYvVcktMUgqM5+CizIM19JZEQq1iZMGF3HRNTqEK+3aJ8HKkT1vTTU+3UptrEiYiRcE1W/RdST6qe+WpH25KdYudAGbmKn4najLj46sQU7sD28Nm4eKuM1ZvhGusqXMQKBgQDecjc1XdQv/vO7wJXbP3KzpJKs+dBAnzZhCBSf55jCvXIhUoTaGcJXGR1oHOV83MH96z4ay+NXaZzHpfy4EP2dEOsHzvZZnKBoyZbubHI9OBFdCTBCi532RST4Baz2lFz6nNzulXAkxEArJxHE37GtzdeyGsyAIx22P3YmC3yvmwKBgQDKOYdbbPXbmfIWjW2QrT38yY1yUydf52AZ5eFjW+vOtr3iV7Dm06MBf3xd3ozh10zTx06tzSX7lfPFTOxr+e6e096HS+TyZXx/jjblwQK0gP6QJzl90VH0a75N12/0ARnQB4k2FJXIwmzNVpqTV3AN0z3bARGrOSDXCaJGbLXMXQKBgFkDWWZIiq0+F2hNM2pNpGeQdoUzXgtN6qO/aQln0rAywOXmsHTaE/NogGT2Ezr418P1C6ALcIi9IVie6lb5rZLZf0NbpSbMzz+/U19LFBe9RO12zNIELoyqtJVvJ/xv+BjtBleOgxajVY4vDyQ5407KbQK/SJAJ/Uy9bljhBd77AoGBALgGiCXrzwRrYIGjbBxneuxqojxybXd5v9txdZ0MjldLtxqdwE8eMs1vLUbi+oOegLyMOA4zjSgYeoCGkvNjuwFwPqYSq6LmNdiCG3UTIpoOdRnLiCb18EWkMsb1kVyUD+Beqz+6ld/fBlIKSJpizIbYQAbnPyP41ei6TKEX1ozFAoGAaGtnxdI5NJGOn20+S6o56i/t6dPkMHuQidiJGoWbJ27RyLksVV3XFVhB71fhIvtVQVrUpQnBDF3iXKBkVzyXwZfujSMH3ZKEJ81Rtxuy7PtJSsF9AboBnZuGqyAkpAIddXmiselaFMSUi58K2p9lS0cUOOhjzByAQYtuSqk7JEE=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4ny6JRefqy4gH4BzzbR6+KttsWPiIscvu2EBIOtOx3QhY+jb/iWC8a36z0v61OR1AOMNaPdxBIDQaJl/XPnDblImHRHB5RVHM/ZFwBTV/oyEIHDl3b7WonmC/WVfJ0oeTzXShc/kMMey++4/ib4rFkptKBadUrJ/XI9N08P6vYEB3dzupQNf02/lZGNyWEoLtC9vNeuTTcc57UXlg7qDlhJ+frlyX/kIJTRcq+CrLKeEgVwxCVjDjAARd/BdpVa5BIkvaf9jJpEnunIGoFfAZDBdXQTyb0YkZsfN1PP1V8M/ekey3SmU7U7D9Dwj0Tkj0jwqvYN9sO+kjfYW8nJ7LwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

