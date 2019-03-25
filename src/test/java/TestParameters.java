class TestParameters {

    private String url;
    private String searchParameters;

    TestParameters (String url, String searchParameters){
        if (url!=null && searchParameters!=null){
            this.url = url;
            this.searchParameters = searchParameters;
        }
    }

    String getUrl() {
        return url;
    }

    String getSearchParameters() {
        return searchParameters;
    }
}
