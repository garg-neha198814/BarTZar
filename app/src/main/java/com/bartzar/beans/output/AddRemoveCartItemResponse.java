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
public class AddRemoveCartItemResponse {

    /**
     * Code : 200
     * Status : OK
     * message : Cart Item Added  Success.
     * result : [{"success":"1","message":"Cart Item Added Successfully","add_remove":"1","cart_items_count":1,"cart_price_total":121,"cart_id":55,"data":null}]
     * CurrentDateTime : {"date":"2016-06-01 12:27:39.000000","timezone_type":3,"timezone":"UTC"}
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
         * date : 2016-06-01 12:27:39.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private CurrentDateTimeBean CurrentDateTime;
        /**
         * success : 1
         * message : Cart Item Added Successfully
         * add_remove : 1
         * cart_items_count : 1
         * cart_price_total : 121
         * cart_id : 55
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
            private String add_remove;
            private int cart_items_count;
            private int cart_price_total;
            private int cart_id;
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

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
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
