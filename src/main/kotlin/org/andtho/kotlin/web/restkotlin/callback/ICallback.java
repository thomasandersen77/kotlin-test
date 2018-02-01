package org.andtho.kotlin.web.restkotlin.callback;

public interface ICallback<T, R> {
    R call(T value);
}
