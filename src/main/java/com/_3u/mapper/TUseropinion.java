package com._3u.mapper;

import java.util.Date;

public class TUseropinion {
    private Integer id;

    private String pcguid;

    private String pcos;

    private String pcvs;

    private String ip;

    private String type;

    private String context;

    private String contactway;

    private String image;

    private Integer servernum;

    private Integer status;

    private String note;

    private String operuser;

    private Date opertime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPcguid() {
        return pcguid;
    }

    public void setPcguid(String pcguid) {
        this.pcguid = pcguid == null ? null : pcguid.trim();
    }

    public String getPcos() {
        return pcos;
    }

    public void setPcos(String pcos) {
        this.pcos = pcos == null ? null : pcos.trim();
    }

    public String getPcvs() {
        return pcvs;
    }

    public void setPcvs(String pcvs) {
        this.pcvs = pcvs == null ? null : pcvs.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getContactway() {
        return contactway;
    }

    public void setContactway(String contactway) {
        this.contactway = contactway == null ? null : contactway.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getServernum() {
        return servernum;
    }

    public void setServernum(Integer servernum) {
        this.servernum = servernum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getOperuser() {
        return operuser;
    }

    public void setOperuser(String operuser) {
        this.operuser = operuser == null ? null : operuser.trim();
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}