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
 * Created by root on 3/6/16.
 */
public class CartDetailResponse {


    /**
     * Code : 200
     * Status : OK
     * message : Cart Item(s) Fetched Successfully.
     * result : [{"success":"1","message":"Cart Item(s) Fetched Successfully","add_remove":"1","cart_items_count":4,"cart_price_total":10002,"data":[{"cart_item_id":"111","menu_item_id":"6","menu_item_name":"item 5","menu_item_price":"","menu_item_qty":"","cart_item_counts":"2","total_sub_item_price":"0"},{"cart_item_id":"114","menu_item_id":"4","menu_item_name":"Item 4th","menu_item_price":"","menu_item_qty":"","cart_item_counts":"1","total_sub_item_price":"0"},{"cart_item_id":"115","menu_item_id":"15","menu_item_name":"item 141","menu_item_price":"10002","menu_item_qty":"","cart_item_counts":"1","total_sub_item_price":"10002"}]}]
     * CurrentDateTime : {"date":"2016-06-03 07:02:56.000000","timezone_type":3,"timezone":"UTC"}
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
         * date : 2016-06-03 07:02:56.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private CurrentDateTimeBean CurrentDateTime;
        /**
         * success : 1
         * message : Cart Item(s) Fetched Successfully
         * add_remove : 1
         * cart_items_count : 4
         * cart_price_total : 10002
         * data : [{"cart_item_id":"111","menu_item_id":"6","menu_item_name":"item 5","menu_item_price":"","menu_item_qty":"","cart_item_counts":"2","total_sub_item_price":"0"},{"cart_item_id":"114","menu_item_id":"4","menu_item_name":"Item 4th","menu_item_price":"","menu_item_qty":"","cart_item_counts":"1","total_sub_item_price":"0"},{"cart_item_id":"115","menu_item_id":"15","menu_item_name":"item 141","menu_item_price":"10002","menu_item_qty":"","cart_item_counts":"1","total_sub_item_price":"10002"}]
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
            private String message;
            private String add_remove;
            private int cart_items_count;
            private int cart_price_total;
            /**
             * cart_item_id : 111
             * menu_item_id : 6
             * menu_item_name : item 5
             * menu_item_price :
             * menu_item_qty :
             * cart_item_counts : 2
             * total_sub_item_price : 0
             */

            private List<DataBean> data;

            public String getSuccess() {
                return success;
            }

            public void setSuccess(String success) {
                this.success = success;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getAdd_remove() {
                return add_remove;
            }

            public void setAdd_remove(String add_remove) {
                this.add_remove = add_remove;
            }

            public int getCart_items_count() {
                return cart_items_count;
            }

            public void setCart_items_count(int cart_items_count) {
                this.cart_items_count = cart_items_count;
            }

            public int getCart_price_total() {
                return cart_price_total;
            }

            public void setCart_price_total(int cart_price_total) {
                this.cart_price_total = cart_price_total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                private String cart_item_id;
                private String menu_item_id;
                private String menu_item_name;
                private String menu_item_price;
                private String menu_item_qty;
                private String cart_item_counts;
                private String total_sub_item_price;

                public String getCart_item_id() {
                    return cart_item_id;
                }

                public void setCart_item_id(String cart_item_id) {
                    this.cart_item_id = cart_item_id;
                }

                public String getMenu_item_id() {
                    return menu_item_id;
                }

                public void setMenu_item_id(String menu_item_id) {
                    this.menu_item_id = menu_item_id;
                }

                public String getMenu_item_name() {
                    return menu_item_name;
                }

                public void setMenu_item_name(String menu_item_name) {
                    this.menu_item_name = menu_item_name;
                }

                public String getMenu_item_price() {
                    return menu_item_price;
                }

                public void setMenu_item_price(String menu_item_price) {
                    this.menu_item_price = menu_item_price;
                }

                public String getMenu_item_qty() {
                    return menu_item_qty;
                }

                public void setMenu_item_qty(String menu_item_qty) {
                    this.menu_item_qty = menu_item_qty;
                }

                public String getCart_item_counts() {
                    return cart_item_counts;
                }

                public void setCart_item_counts(String cart_item_counts) {
                    this.cart_item_counts = cart_item_counts;
                }

                public String getTotal_sub_item_price() {
                    return total_sub_item_price;
                }

                public void setTotal_sub_item_price(String total_sub_item_price) {
                    this.total_sub_item_price = total_sub_item_price;
                }
            }
        }
    }
}
