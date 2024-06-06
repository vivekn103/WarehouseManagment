package com.jsp.warehouse_manager.utility;


public class ResponseStructure<T> {
    private int status;
        private String message;
        private T data;
        
        public int getStatuscode() {
            return status;
        }
        public ResponseStructure<T> setStatuscode(int status) {
            this.status = status;
            return this;
        }
        public String getMessage() {
            return message;
        }
        public ResponseStructure<T> setMessage(String message) {
            this.message = message;
            return this;
        }
        public T getData() {
            return data;
        }
        public ResponseStructure<T> setData(T actor) {
            this.data = actor;
            return this;
        } 	
}
