package com.example.dijingyu.app_demo.bean;

/**
 * 作者：邸某某
 * 时间：2019/5/7
 */

public class PersonageInfoBean {
    /**
     * code : 0
     * desc :
     * result : {"uid":492736,"description":"你又不是伞，何必硬撑呢","balance":"0.00","userName":"邸某某","photo":"http://cdn.banmi.com/banmiapp/user/logo/1557208893294.jpg","gender":"M","email":"","phone":""}
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
         * uid : 492736
         * description : 你又不是伞，何必硬撑呢
         * balance : 0.00
         * userName : 邸某某
         * photo : http://cdn.banmi.com/banmiapp/user/logo/1557208893294.jpg
         * gender : M
         * email :
         * phone :
         */

        private int uid;
        private String description;
        private String balance;
        private String userName;
        private String photo;
        private String gender;
        private String email;
        private String phone;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
