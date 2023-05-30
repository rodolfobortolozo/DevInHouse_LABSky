package tech.devinhouse.labsky.handler;

public record ErrorObject(
        String field,
        String message,
        Object parameter) {

}
