package com.example.dijingyu.app_demo.bean;

import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/13
 */

public class InfoPathBean {
    /**
     * code : 0
     * desc :
     * result : {"count":1,"page":1,"limit":20,"routes":[{"id":90,"cityID":10,"priceInCents":190,"title":"洛北","intro":"6小时漫步京都山间皇家园林","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510740818199_80f76367a79f3439ce5f633d7a58c532.jpg","videoURL":"","sequence":1847,"isPurchased":false,"isCollected":false,"city":"日本·京都","price":"1.9","date":"2017-06-07 13:26"}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * count : 1
         * page : 1
         * limit : 20
         * routes : [{"id":90,"cityID":10,"priceInCents":190,"title":"洛北","intro":"6小时漫步京都山间皇家园林","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510740818199_80f76367a79f3439ce5f633d7a58c532.jpg","videoURL":"","sequence":1847,"isPurchased":false,"isCollected":false,"city":"日本·京都","price":"1.9","date":"2017-06-07 13:26"}]
         */

        private int count;
        private int page;
        private int limit;
        private List<RoutesBean> routes;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<RoutesBean> getRoutes() {
            return routes;
        }

        public void setRoutes(List<RoutesBean> routes) {
            this.routes = routes;
        }

        public static class RoutesBean {
            /**
             * id : 90
             * cityID : 10
             * priceInCents : 190
             * title : 洛北
             * intro : 6小时漫步京都山间皇家园林
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1510740818199_80f76367a79f3439ce5f633d7a58c532.jpg
             * videoURL :
             * sequence : 1847
             * isPurchased : false
             * isCollected : false
             * city : 日本·京都
             * price : 1.9
             * date : 2017-06-07 13:26
             */

            private int id;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String price;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getVideoURL() {
                return videoURL;
            }

            public void setVideoURL(String videoURL) {
                this.videoURL = videoURL;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public boolean isIsCollected() {
                return isCollected;
            }

            public void setIsCollected(boolean isCollected) {
                this.isCollected = isCollected;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
