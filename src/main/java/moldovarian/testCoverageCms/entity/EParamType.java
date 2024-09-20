package moldovarian.testCoverageCms.entity;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum EParamType {
    PATH("path"),
    QUERY("query"),
    BODY("body");

    private final String value;
    private static final Map<String, EParamType> LOOKUP_MAP = new HashMap<>();

    static {
        for (EParamType paramType : values()) {
            LOOKUP_MAP.put(paramType.value, paramType);
        }
    }

    public static EParamType lookUp(String value) {
        return LOOKUP_MAP.get(value);
    }
}
