public enum Method {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String str;

    Method(String str) {
        this.str = str;
    }
}
