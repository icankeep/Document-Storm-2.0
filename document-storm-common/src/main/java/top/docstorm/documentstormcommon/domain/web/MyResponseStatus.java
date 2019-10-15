package top.docstorm.documentstormcommon.domain.web;

/**
 * @Description:
 * @author: passer
 * @version：2019/9/15
 */
public class MyResponseStatus {
    // 状态码，0表示成功，-1表示失败
    int status;

    // 返回的信息
    String msg;

    public MyResponseStatus() {
    }

    public MyResponseStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
