package com.stylefeng.guns.rest.modular.auth.VO;

public class ResponseVO<M> {
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

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }

    //返回状态
    private  int status;
    //返回信息
    private  String msg;

    //返回数据实体
    private  M  data;

    private ResponseVO(){}

    public static<M> ResponseVO sucess(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        return responseVO;
    }
    public static<M> ResponseVO sucess(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);
        return responseVO;
    }
    public static<M> ResponseVO serviceFail(String msg ){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);
        return responseVO;
    }
    public static<M> ResponseVO appFail(String msg ){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);
        return responseVO;
    }


}
