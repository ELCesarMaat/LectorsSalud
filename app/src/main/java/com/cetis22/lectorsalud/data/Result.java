package com.cetis22.lectorsalud.data;

/* loaded from: classes4.dex */
public class Result<T> {
    private Result() {
    }

    public String toString() {
        if (this instanceof Success) {
            Success success = (Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Error) {
            Error error = (Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        } else {
            return "";
        }
    }

    /* loaded from: classes4.dex */
    public static final class Success<T> extends Result {
        private T data;

        public Success(T data) {
            super();
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    /* loaded from: classes4.dex */
    public static final class Error extends Result {
        private Exception error;

        public Error(Exception error) {
            super();
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}
