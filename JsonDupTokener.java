public class JsonDupTokener extends JSONTokener {

    public JsonDupTokener(String s) {
        super(s);
    }

    @Override
    public Object nextValue() throws JSONException {
        char c = this.nextClean();
        switch (c) {
            case '\"':
            case '\'':
                return this.nextString(c);
            case '[':
                this.back();
                return new JSONArray(this);
            case '{':
                this.back();
                return new JSONObjectIgnoreDuplicates(this);
            default:
                StringBuffer sb;
                for (sb = new StringBuffer(); c >= 32 && ",:]}/\\\"[{;=#".indexOf(c) < 0; c = this.next()) {
                    sb.append(c);
                }

                this.back();
                String string = sb.toString().trim();
                if ("".equals(string)) {
                    throw this.syntaxError("Missing value");
                } else {
                    return JSONObject.stringToValue(string);
                }
        }
    }
}
