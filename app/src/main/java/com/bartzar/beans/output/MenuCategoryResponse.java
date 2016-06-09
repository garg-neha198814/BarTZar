/*
 *
 *  *
 *  *  * Copyright (c) 2016, Mobilyte Tech India Pvt. Ltd. and/or its affiliates. All rights reserved.
 *  *  *
 *  *  * Redistribution and use in source and binary forms, with or without
 *  *  * modification, are permitted provided that the following conditions are met:
 *  *  *
 *  *  *  - Redistributions of source code must retain the above copyright
 *  *  *    notice, this list of conditions and the following disclaimer.
 *  *  *
 *  *  *  - Redistributions in binary form must reproduce the above copyright
 *  *  *    notice, this list of conditions and the following disclaimer in the
 *  *  *    documentation and/or other materials provided with the distribution.
 *  *
 *
 */

package com.bartzar.beans.output;

import java.util.List;

/**
 * Created by root on 1/6/16.
 */
public class MenuCategoryResponse {

    /**
     * Code : 200
     * Status : OK
     * message : Menu Catories got Successfully
     * result : [{"success":"1","total_records":"9","cart_counts":0,"cart_items_count":0,"total_cart_price":0,"data":[{"id":"1","name":"Beers","image":"HGkqGDtNzRlN3DT.png","description":"here desc about Beers Category","created_at":"2016-05-30 22:39:16","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/HGkqGDtNzRlN3DT.png"},{"id":"2","name":"Wines","image":"f5deK1aY3BzGhFF.png","description":"here desc about Wines category","created_at":"2016-05-30 22:40:30","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/f5deK1aY3BzGhFF.png"},{"id":"3","name":"Sparkling","image":"DA99zZ5xQjHtH4U.png","description":"here desc about Sparkling","created_at":"2016-05-30 22:41:04","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/DA99zZ5xQjHtH4U.png"},{"id":"4","name":"Spirit","image":"EMUAo57aCL8vQ3w.png","description":"here desc about Spirit category","created_at":"2016-05-30 22:41:34","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/EMUAo57aCL8vQ3w.png"},{"id":"6","name":"Cocktails","image":"AUSBVJy8q18Sxgx.png","description":"here desc. about Cocktails","created_at":"2016-05-30 22:41:58","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/AUSBVJy8q18Sxgx.png"},{"id":"7","name":"Soft Drinks","image":"Z1UScTIqf7iRsP4.png","description":"here desc. about Soft Drinks","created_at":"2016-05-30 22:42:29","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/Z1UScTIqf7iRsP4.png"},{"id":"8","name":"Cofee & Tee","image":"JajYcKbfddydgBI.png","description":"Here desc. about Cofee & Tee.","created_at":"2016-05-30 22:43:20","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/JajYcKbfddydgBI.png"},{"id":"9","name":"Bar Snacks","image":"dIutjnHB8Zt50DH.png","description":"here desc about Bar Snacks","created_at":"2016-05-30 22:46:22","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/dIutjnHB8Zt50DH.png"},{"id":"10","name":"Food","image":"7Ipe3vhVpx1hZjy.png","description":"here descr about Food.","created_at":"2016-05-30 22:46:49","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/7Ipe3vhVpx1hZjy.png"}],"message":"Menu Categories Successfully"}]
     * CurrentDateTime : {"date":"2016-06-03 09:23:39.000000","timezone_type":3,"timezone":"UTC"}
     */

    private ResponseBean Response;

    public ResponseBean getResponse() {
        return Response;
    }

    public void setResponse(ResponseBean Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        private int Code;
        private String Status;
        private String message;
        /**
         * date : 2016-06-03 09:23:39.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private CurrentDateTimeBean CurrentDateTime;
        /**
         * success : 1
         * total_records : 9
         * cart_counts : 0
         * cart_items_count : 0
         * total_cart_price : 0
         * data : [{"id":"1","name":"Beers","image":"HGkqGDtNzRlN3DT.png","description":"here desc about Beers Category","created_at":"2016-05-30 22:39:16","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/HGkqGDtNzRlN3DT.png"},{"id":"2","name":"Wines","image":"f5deK1aY3BzGhFF.png","description":"here desc about Wines category","created_at":"2016-05-30 22:40:30","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/f5deK1aY3BzGhFF.png"},{"id":"3","name":"Sparkling","image":"DA99zZ5xQjHtH4U.png","description":"here desc about Sparkling","created_at":"2016-05-30 22:41:04","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/DA99zZ5xQjHtH4U.png"},{"id":"4","name":"Spirit","image":"EMUAo57aCL8vQ3w.png","description":"here desc about Spirit category","created_at":"2016-05-30 22:41:34","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/EMUAo57aCL8vQ3w.png"},{"id":"6","name":"Cocktails","image":"AUSBVJy8q18Sxgx.png","description":"here desc. about Cocktails","created_at":"2016-05-30 22:41:58","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/AUSBVJy8q18Sxgx.png"},{"id":"7","name":"Soft Drinks","image":"Z1UScTIqf7iRsP4.png","description":"here desc. about Soft Drinks","created_at":"2016-05-30 22:42:29","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/Z1UScTIqf7iRsP4.png"},{"id":"8","name":"Cofee & Tee","image":"JajYcKbfddydgBI.png","description":"Here desc. about Cofee & Tee.","created_at":"2016-05-30 22:43:20","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/JajYcKbfddydgBI.png"},{"id":"9","name":"Bar Snacks","image":"dIutjnHB8Zt50DH.png","description":"here desc about Bar Snacks","created_at":"2016-05-30 22:46:22","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/dIutjnHB8Zt50DH.png"},{"id":"10","name":"Food","image":"7Ipe3vhVpx1hZjy.png","description":"here descr about Food.","created_at":"2016-05-30 22:46:49","image_src":"http://bartzar.betasoftdev.com/img/uploads/menu_category/7Ipe3vhVpx1hZjy.png"}]
         * message : Menu Categories Successfully
         */

        private List<ResultBean> result;

        public int getCode() {
            return Code;
        }

        public void setCode(int Code) {
            this.Code = Code;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public CurrentDateTimeBean getCurrentDateTime() {
            return CurrentDateTime;
        }

        public void setCurrentDateTime(CurrentDateTimeBean CurrentDateTime) {
            this.CurrentDateTime = CurrentDateTime;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class CurrentDateTimeBean {
            private String date;
            private int timezone_type;
            private String timezone;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getTimezone_type() {
                return timezone_type;
            }

            public void setTimezone_type(int timezone_type) {
                this.timezone_type = timezone_type;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }
        }

        public static class ResultBean {
            private String success;
            private String total_records;
            private int cart_counts;
            private int cart_items_count;
            private int total_cart_price;
            private String message;
            /**
             * id : 1
             * name : Beers
             * image : HGkqGDtNzRlN3DT.png
             * description : here desc about Beers Category
             * created_at : 2016-05-30 22:39:16
             * image_src : http://bartzar.betasoftdev.com/img/uploads/menu_category/HGkqGDtNzRlN3DT.png
             */

            private List<DataBean> data;

            public String getSuccess() {
                return success;
            }

            public void setSuccess(String success) {
                this.success = success;
            }

            public String getTotal_records() {
                return total_records;
            }

            public void setTotal_records(String total_records) {
                this.total_records = total_records;
            }

            public int getCart_counts() {
                return cart_counts;
            }

            public void setCart_counts(int cart_counts) {
                this.cart_counts = cart_counts;
            }

            public int getCart_items_count() {
                return cart_items_count;
            }

            public void setCart_items_count(int cart_items_count) {
                this.cart_items_count = cart_items_count;
            }

            public int getTotal_cart_price() {
                return total_cart_price;
            }

            public void setTotal_cart_price(int total_cart_price) {
                this.total_cart_price = total_cart_price;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                private String id;
                private String name;
                private String image;
                private String description;
                private String created_at;
                private String image_src;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getImage_src() {
                    return image_src;
                }

                public void setImage_src(String image_src) {
                    this.image_src = image_src;
                }
            }
        }
    }
}
