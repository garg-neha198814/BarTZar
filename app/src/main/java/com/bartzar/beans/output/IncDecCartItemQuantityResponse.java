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
public class IncDecCartItemQuantityResponse {

    /**
     * Code : 200
     * Status : OK
     * message : Cart Item Added  Success.
     * result : [{"success":"1","message":"Cart Item Added Successfully","inc_dec":"inc","qunatity_updated":"3","price_updated":"24","menu_item_id":"4","cart_items_count":2,"cart_price_total":33,"data":null}]
     * CurrentDateTime : {"date":"2016-06-03 07:49:33.000000","timezone_type":3,"timezone":"UTC"}
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
         * date : 2016-06-03 07:49:33.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private CurrentDateTimeBean CurrentDateTime;
        /**
         * success : 1
         * message : Cart Item Added Successfully
         * inc_dec : inc
         * qunatity_updated : 3
         * price_updated : 24
         * menu_item_id : 4
         * cart_items_count : 2
         * cart_price_total : 33
         * data : null
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
            private String inc_dec;
            private String qunatity_updated;
            private String price_updated;
            private String menu_item_id;
            private int cart_items_count;
            private int cart_price_total;
            private Object data;

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

            public String getInc_dec() {
                return inc_dec;
            }

            public void setInc_dec(String inc_dec) {
                this.inc_dec = inc_dec;
            }

            public String getQunatity_updated() {
                return qunatity_updated;
            }

            public void setQunatity_updated(String qunatity_updated) {
                this.qunatity_updated = qunatity_updated;
            }

            public String getPrice_updated() {
                return price_updated;
            }

            public void setPrice_updated(String price_updated) {
                this.price_updated = price_updated;
            }

            public String getMenu_item_id() {
                return menu_item_id;
            }

            public void setMenu_item_id(String menu_item_id) {
                this.menu_item_id = menu_item_id;
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

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }
        }
    }
}
