package com.xiarh.purenews.bean;

/**
 * Created by xiarh on 2017/5/31.
 */

public class UpdateBean {

    /**
     * name : PureNews
     * version : 1
     * changelog : 首次上传
     * updated_at : 1496214855
     * versionShort : 1.0
     * build : 1
     * installUrl : http://download.fir.im/v2/app/install/592e6d2d548b7a1ccf000135?download_token=ccefac6c7b1dc161d0a0bfab3f2fb2fd&source=update
     * install_url : http://download.fir.im/v2/app/install/592e6d2d548b7a1ccf000135?download_token=ccefac6c7b1dc161d0a0bfab3f2fb2fd&source=update
     * direct_install_url : http://download.fir.im/v2/app/install/592e6d2d548b7a1ccf000135?download_token=ccefac6c7b1dc161d0a0bfab3f2fb2fd&source=update
     * update_url : http://fir.im/PureNews
     * binary : {"fsize":2941889}
     */

    private String name;
    private String version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String install_url;
    private String update_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }
}
