package teapot.models;

import teapot.utils.RequirementHandler;
import teapot.utils.enums.ResponseCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Response {
    private final LinkedList<String> data;
    private ResponseCode code = ResponseCode.SUCCESS;

    public Response() {
        data = new LinkedList<>();
    }

    public Response print(String s) {
        data.add(RequirementHandler.requireNonEmptyString(s));
        return this;
    }

    public Response print(Object o) {
        data.add(RequirementHandler.requireNonEmptyString(o.toString()));
        return this;
    }

    public Response err(String s) {
        code = ResponseCode.EXECUTED_WITH_ERROR;
        data.add(RequirementHandler.requireNonEmptyString(s));
        return this;
    }

    public ResponseCode getCode() {
        return code;
    }

    public Response setCode(ResponseCode code) {
        this.code = Objects.requireNonNull(code);
        return this;
    }

    public LinkedList<String> getData() {
        return data;
    }
}
