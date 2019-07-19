// string "json" with duplicate keys
String json = "{\"Sign_In_Type\": \"Action\", \"Sign_In_Type\": \"Action\"}";

try {
    JSONObject json_obj = new JSONObjectIgnoreDuplicates(new JSONDupTokener(json));
    String type = json_obj.getString("Sign_In_Type");
} catch (JSONException e) {
    throw new RuntimeException(e);
}
