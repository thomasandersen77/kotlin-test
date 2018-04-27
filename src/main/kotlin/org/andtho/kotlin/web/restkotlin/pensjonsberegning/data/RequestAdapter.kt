package org.andtho.kotlin.web.restkotlin.pensjonsberegning.data

interface RequestAdapter<T> {
    fun id() : T
    fun content() : T
}