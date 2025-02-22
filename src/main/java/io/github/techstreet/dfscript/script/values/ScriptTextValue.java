package io.github.techstreet.dfscript.script.values;

import com.google.gson.*;
import net.kyori.adventure.platform.fabric.FabricClientAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.text.Text;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class ScriptTextValue extends ScriptValue {

    private final String value;

    public ScriptTextValue(String value) {
        this.value = value;
    }

    @Override
    String typeName() {
        return "Text";
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

    @Override
    public Text formatAsText() {
        return FabricClientAudiences.of().toNative(parse());
    }

    public Component parse() {
        return MiniMessage.miniMessage().deserialize(value);
    }

    public static class Serializer implements JsonSerializer<ScriptTextValue>, JsonDeserializer<ScriptTextValue> {
        @Override
        public ScriptTextValue deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive prim = jsonElement.getAsJsonPrimitive();
                if (prim.isString()) {
                    return new ScriptTextValue(prim.getAsString());
                }
            }

            throw new JsonParseException("Unable to convert the json into a script text value!");
        }

        @Override
        public JsonElement serialize(ScriptTextValue scriptValue, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(scriptValue.asString());
        }
    }
}
