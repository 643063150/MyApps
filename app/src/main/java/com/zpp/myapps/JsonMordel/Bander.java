package com.zpp.myapps.JsonMordel;

import java.util.List;

/**
 * Created by admins on 2016/4/22.
 */
public class Bander {

    /**
     * list : [{"adHeight":280,"adWidth":662,"adsTypesID":1,"height":280,"iD":94,"name":"城市热线商家入驻有礼","picID":"http://image.rexian.cn/Upload/20150818/Spread/15/20150818_Spread_15_153814515300.jpg","sort":0,"tar":"_blank","url":"#","width":662},{"adHeight":280,"adWidth":662,"adsTypesID":1,"height":280,"iD":93,"name":"开心 带回家","picID":"http://image.rexian.cn/Upload/20150818/Spread/15/20150818_Spread_15_154000538045.jpg","sort":0,"tar":"_blank","url":"#","width":662},{"adHeight":280,"adWidth":662,"adsTypesID":1,"height":280,"iD":92,"name":"城市热线招商进行中","picID":"http://image.rexian.cn/Upload/20150818/Spread/15/20150818_Spread_15_154108680341.jpg","sort":0,"tar":"_blank","url":"#","width":662},{"adHeight":280,"adWidth":662,"adsTypesID":1,"height":280,"iD":91,"name":"马上有奖","picID":"http://image.rexian.cn/Upload/20150123/Spread/09/20150123_Spread_09_090132225237.jpg","sort":0,"tar":"_blank","url":"#","width":662}]
     * status : 0
     * statusMsg : 请求成功
     */

    private int status;
    private String statusMsg;
    /**
     * adHeight : 280
     * adWidth : 662
     * adsTypesID : 1
     * height : 280
     * iD : 94
     * name : 城市热线商家入驻有礼
     * picID : http://image.rexian.cn/Upload/20150818/Spread/15/20150818_Spread_15_153814515300.jpg
     * sort : 0
     * tar : _blank
     * url : #
     * width : 662
     */

    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int adHeight;
        private int adWidth;
        private int adsTypesID;
        private int height;
        private int iD;
        private String name;
        private String picID;
        private int sort;
        private String tar;
        private String url;
        private int width;

        public int getAdHeight() {
            return adHeight;
        }

        public void setAdHeight(int adHeight) {
            this.adHeight = adHeight;
        }

        public int getAdWidth() {
            return adWidth;
        }

        public void setAdWidth(int adWidth) {
            this.adWidth = adWidth;
        }

        public int getAdsTypesID() {
            return adsTypesID;
        }

        public void setAdsTypesID(int adsTypesID) {
            this.adsTypesID = adsTypesID;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getID() {
            return iD;
        }

        public void setID(int iD) {
            this.iD = iD;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicID() {
            return picID;
        }

        public void setPicID(String picID) {
            this.picID = picID;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTar() {
            return tar;
        }

        public void setTar(String tar) {
            this.tar = tar;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
