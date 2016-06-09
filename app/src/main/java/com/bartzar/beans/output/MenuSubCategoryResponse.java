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
public class MenuSubCategoryResponse {

    /**
     * Code : 200
     * Status : OK
     * message : Menu Items fetched Successfully
     * result : [{"success":"1","total_records":"2","cart_counts":2,"cart_items_count":null,"total_cart_price":0,"data":[{"id":"2","name":"Pliny the Elder","image":"dZJqHTrqklfDqaB.jpg","quantity":"500 ML","price":"9","description":"Pliny The Elder is a American Double / Imperial IPA style beer brewed by Russian River Brewing Company in Santa Rosa, CA.","created_at":"2016-06-03 00:27:41","menu_category_id":"1","is_cart_item":0},{"id":"3","name":"60 Minute Dogfish","image":"Dtp4CepJEi6owzs.jpg","quantity":"500 Ml","price":"12","description":"60 Minute IPA is continuously hopped -- more than 60 hop additions over a 60-minute boil. ","created_at":"2016-06-03 00:28:08","menu_category_id":"1","is_cart_item":0}],"message":"Menu Items fetched Successfully"}]
     * CurrentDateTime : {"date":"2016-06-03 09:24:17.000000","timezone_type":3,"timezone":"UTC"}
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
         * date : 2016-06-03 09:24:17.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private CurrentDateTimeBean CurrentDateTime;
        /**
         * success : 1
         * total_records : 2
         * cart_counts : 2
         * cart_items_count : null
         * total_cart_price : 0
         * data : [{"id":"2","name":"Pliny the Elder","image":"dZJqHTrqklfDqaB.jpg","quantity":"500 ML","price":"9","description":"Pliny The Elder is a American Double / Imperial IPA style beer brewed by Russian River Brewing Company in Santa Rosa, CA.","created_at":"2016-06-03 00:27:41","menu_category_id":"1","is_cart_item":0},{"id":"3","name":"60 Minute Dogfish","image":"Dtp4CepJEi6owzs.jpg","quantity":"500 Ml","price":"12","description":"60 Minute IPA is continuously hopped -- more than 60 hop additions over a 60-minute boil. ","created_at":"2016-06-03 00:28:08","menu_category_id":"1","is_cart_item":0}]
         * message : Menu Items fetched Successfully
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
            private Object cart_items_count;
            private int total_cart_price;
            private String message;
            /**
             * id : 2
             * name : Pliny the Elder
             * image : dZJqHTrqklfDqaB.jpg
             * quantity : 500 ML
             * price : 9
             * description : Pliny The Elder is a American Double / Imperial IPA style beer brewed by Russian River Brewing Company in Santa Rosa, CA.
             * created_at : 2016-06-03 00:27:41
             * menu_category_id : 1
             * is_cart_item : 0
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

            public Object getCart_items_count() {
                return cart_items_count;
            }

            public void setCart_items_count(Object cart_items_count) {
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
                private String quantity;
                private String price;
                private String description;
                private String created_at;
                private String menu_category_id;
                private int is_cart_item;

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

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
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

                public String getMenu_category_id() {
                    return menu_category_id;
                }

                public void setMenu_category_id(String menu_category_id) {
                    this.menu_category_id = menu_category_id;
                }

                public int getIs_cart_item() {
                    return is_cart_item;
                }

                public void setIs_cart_item(int is_cart_item) {
                    this.is_cart_item = is_cart_item;
                }
            }
        }
    }
}
