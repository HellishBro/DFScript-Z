package io.github.techstreet.dfscript.script.values;

import com.google.gson.*;
import net.minecraft.text.Text;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class ScriptStringValue extends ScriptValue {

    private final String value;

    public ScriptStringValue(String value) {
        this.value = value;
    }

    @Override
    String typeName() {
        return "String";
    }


    @Override
    public String asString() {
        return value;
    }

    @Override
    public boolean asBoolean() {
        return !value.equals("");
    }

    @Override
    public boolean valueEquals(ScriptValue other) {
        return other.asString().equals(value);
    }

    public static class Serializer implements JsonSerializer<ScriptStringValue>, JsonDeserializer<ScriptStringValue> {
        @Override
        public ScriptStringValue deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            return new ScriptStringValue(jsonElement.getAsString());
        }

        @Override
        public JsonElement serialize(ScriptStringValue scriptValue, Type type, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.add("string", new JsonPrimitive(scriptValue.value));
            obj.addProperty("___objectType", "string");
            return obj;
        }
    }
}
