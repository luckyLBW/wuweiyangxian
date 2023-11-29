package com.wuweiyangxian.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResultBean {
    /**
     * userinfo : {"id":9,"username":"13695424587","phone":"13695424587","token":null,"head_image":"http://43.140.247.202:10028/storage/system/20230712/e4b23bbce9feb2fb36b595d1bf6f614e.png","email":"65788@234.iii","status":1,"attestation_company":3,"attestation_person":1,"create_time":"2023-04-18 13:58:56","update_time":"2023-07-31 09:59:02","group_ids":[5,6],"nickname":"张小欧","province":530000,"city":530400,"area":530403,"address":"丛台大厦","introduce":"个人简介个人简介个人简介个人简介","admin_id":null,"balance":"1178.25","balance_withdraw":"1099.25","is_complete":1,"category_id":13,"development_stage":1,"service":{"0":1},"account_type":1,"account_status":2,"attestation_type":2,"attestation_status":2,"recommend_status":1,"groups_name":"认证个人,普通用户"}
     * permissions : ["/api/service/edit","/api/goods/add","/api/goods/edit","/api/course/index","/api/course/add","/api/course/edit","/api/course/delete","/api/course/status","/api/course.content/index","/api/course.content/add","/api/course.content/edit","/api/course.content/delete","/api/goods/mine","/api/stock.right/index","/api/solution/index","/api/solution/find","/api/solution.info/index","/api/requirements/index","/api/service/index","/api/soft/index","/api/device/index","/api/attestation.person/submit","/api/attestation.company/submit","/api/attestation.person/info","/api/attestation.company/info","/api/requirements/add","/api/requirements/delete","/api/requirements.join/add","/api/requirements/status","/api/requirements/mine","/api/requirements/join","/api/requirements.join/index","/api/requirements.join/find","/api/requirements.join/status","/api/goods.comment/add","/api/notice/index","/api/bank.card/index","/api/bank.card/add","/api/bank.card/delete","/api/bank.card/send","/api/invoice/unbilled","/api/invoice/index","/api/invoice/apply","/api/invoice/send","/api/invoice/applyList","/api/community.news/add","/api/community.software/add","/api/community.news/index","/api/community.software/index","/api/stock.right/add","/api/requirements/edit","/api/news/find","/api/news/index","/api/order.goods/find","/api/community.software/find","/api/community.news/find","/api/goods/status","/api/goods/delete","/api/goods/asset","/api/stock.right/received","/api/stock.right/find","/api/stock.right/status","/api/service/find","/api/service/comment","/api/goods/financialApply","/api/requirements.join/edit","/api/ocr/ocr","/api/system.config/getStsConfig","/api/account.attestation/submit","/api/account.attestation/info"]
     * token : 9a6af637-f481-1868-27cc-8923d96c3235
     */

    private UserinfoBean userinfo;
    private String token;

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserinfoBean {
        /**
         * id : 9
         * username : 13695424587
         * phone : 13695424587
         * token : null
         * head_image : http://43.140.247.202:10028/storage/system/20230712/e4b23bbce9feb2fb36b595d1bf6f614e.png
         * email : 65788@234.iii
         * status : 1
         * attestation_company : 3
         * attestation_person : 1
         * create_time : 2023-04-18 13:58:56
         * update_time : 2023-07-31 09:59:02
         * group_ids : [5,6]
         * nickname : 张小欧
         * province : 530000
         * city : 530400
         * area : 530403
         * address : 丛台大厦
         * introduce : 个人简介个人简介个人简介个人简介
         * admin_id : null
         * balance : 1178.25
         * balance_withdraw : 1099.25
         * is_complete : 1
         * category_id : 13
         * development_stage : 1
         * service : {"0":1}
         * account_type : 1
         * account_status : 2
         * attestation_type : 2
         * attestation_status : 2
         * recommend_status : 1
         * groups_name : 认证个人,普通用户
         */

        private int id;
        private String username;
        private String phone;
        private Object token;
        private String head_image;
        private String email;
        private int status;
        private int attestation_company;
        private int attestation_person;
        private String create_time;
        private String update_time;
        private String nickname;
        private int province;
        private int city;
        private int area;
        private String address;
        private String introduce;
        private Object admin_id;
        private String balance;
        private String balance_withdraw;
        private int is_complete;
        private int category_id;
        private int development_stage;
        private ServiceBean service;
        private int account_type;
        private int account_status;
        private int attestation_type;
        private int attestation_status;
        private int recommend_status;
        private String groups_name;
        private List<Integer> group_ids;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public String getHead_image() {
            return head_image;
        }

        public void setHead_image(String head_image) {
            this.head_image = head_image;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAttestation_company() {
            return attestation_company;
        }

        public void setAttestation_company(int attestation_company) {
            this.attestation_company = attestation_company;
        }

        public int getAttestation_person() {
            return attestation_person;
        }

        public void setAttestation_person(int attestation_person) {
            this.attestation_person = attestation_person;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public Object getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(Object admin_id) {
            this.admin_id = admin_id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBalance_withdraw() {
            return balance_withdraw;
        }

        public void setBalance_withdraw(String balance_withdraw) {
            this.balance_withdraw = balance_withdraw;
        }

        public int getIs_complete() {
            return is_complete;
        }

        public void setIs_complete(int is_complete) {
            this.is_complete = is_complete;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getDevelopment_stage() {
            return development_stage;
        }

        public void setDevelopment_stage(int development_stage) {
            this.development_stage = development_stage;
        }

        public ServiceBean getService() {
            return service;
        }

        public void setService(ServiceBean service) {
            this.service = service;
        }

        public int getAccount_type() {
            return account_type;
        }

        public void setAccount_type(int account_type) {
            this.account_type = account_type;
        }

        public int getAccount_status() {
            return account_status;
        }

        public void setAccount_status(int account_status) {
            this.account_status = account_status;
        }

        public int getAttestation_type() {
            return attestation_type;
        }

        public void setAttestation_type(int attestation_type) {
            this.attestation_type = attestation_type;
        }

        public int getAttestation_status() {
            return attestation_status;
        }

        public void setAttestation_status(int attestation_status) {
            this.attestation_status = attestation_status;
        }

        public int getRecommend_status() {
            return recommend_status;
        }

        public void setRecommend_status(int recommend_status) {
            this.recommend_status = recommend_status;
        }

        public String getGroups_name() {
            return groups_name;
        }

        public void setGroups_name(String groups_name) {
            this.groups_name = groups_name;
        }

        public List<Integer> getGroup_ids() {
            return group_ids;
        }

        public void setGroup_ids(List<Integer> group_ids) {
            this.group_ids = group_ids;
        }

        public static class ServiceBean {
            /**
             * 0 : 1
             */

            @SerializedName("0")
            private int _$0;

            public int get_$0() {
                return _$0;
            }

            public void set_$0(int _$0) {
                this._$0 = _$0;
            }
        }
    }
}
