class TestParameters {

    private String url;
    private String searchParameters;

    // This is a class to create an object to hold the test parameters received from the database
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
