1. Follow Documentation on
    http://square.github.io/retrofit/

2. Add following Gradle Libraries to the project
    compile 'com.squareup.retrofit2:retrofit:2.0.1'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'

3. Implement the APIResponseListener in your Activity or Fragment.
@Override
    public void onSuccess(Call<?> call, Response<?> response, String tag) {
        // hide the progress dialog
        // TODo
        try {
            if (response.isSuccessful()) {

            } else {
                System.out.println(response.errorBody().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<?> call, Throwable t, String tag) {
        // hide the progress dialog
        // TODo
        t.fillInStackTrace();
    }

4. Calling the API
    // example of login API
    APIHandler.getInstance().login("goyaldeepak", "deepak123", this);

5. Parsing the Result, then
    a) If Response is ResponseBody, then SUCCESS is
    String result = ((ResponseBody)response.body).string();

    b) If Response is custom Bean Class, then SUCCESS is
    YOUR_BEAN_CLASS object = ((YOUR_BEAN_CLASS)response);

    c) ERROR is Always
    String error = response.errorBody().string();

    d) If Downloading the file, then SUCCESS is
    byte[] bytes = ((ResponseBody) response.body()).bytes();
    // write the above bytes to file using FileOutputStream