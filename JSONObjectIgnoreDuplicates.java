public class JSONObjectIgnoreDuplicates extends JSONObject {

    public JSONObjectIgnoreDuplicates(JSONTokener x) throws JSONException {
        super(x);
    }

    @Override
    public JSONObject putOnce(String key, Object value) throws JSONException {
        Object storedValue;
        if (key != null && value != null) {
            if ((storedValue = this.opt(key)) != null) {
                //Only throw Exception for different values with same key
                if (!storedValue.toString().equals(value.toString())) 
                    throw new JSONException("Duplicate key \"" + key + "\"");
                else
                    return this;
            }
            this.put(key, value);
        }
        return this;
    }
}
