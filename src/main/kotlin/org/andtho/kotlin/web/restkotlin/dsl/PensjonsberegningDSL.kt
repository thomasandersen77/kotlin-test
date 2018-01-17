package org.andtho.kotlin.web.restkotlin.dsl

import java.math.BigDecimal
import java.time.LocalDate
import java.util.Calendar.*

enum class Type {
    GRUNNPENSJON,
    TILLEGGSPENSJON
}


interface Beregning {
    fun getBeregningsType(): Type
    fun beregn(grunnlag: Grunnlag): Beregning
}

class Grunnpensjon() : Beregning {
    override fun getBeregningsType(): Type {
        return Type.GRUNNPENSJON
    }


    override fun beregn(grunnlag: Grunnlag): Beregning {

        val beregnGrunnpensjonAndel = beregnGrunnpensjonAndel(grunnlag.harEktefelleFTR(), grunnlag.uttaksdato())

        return this
    }
}

interface Grunnlag {

    fun harEktefelleFTR(): Boolean
    fun uttaksdato(): LocalDate
}

fun beregnGrunnpensjonAndel(harEktefelleMedFtr: Boolean, uttaksdato: LocalDate): BigDecimal {
    if (harEktefelleMedFtr) {
        if (uttaksdato.isBefore(LocalDate.of(2003, MAY, 1))) {
            return BigDecimal.valueOf(0.75)
        } else if (erMellom(uttaksdato, LocalDate.of(2003, MAY, 1), LocalDate.of(2004, APRIL, 30))) {
            return BigDecimal.valueOf(0.8)
        } else if (erMellom(uttaksdato, LocalDate.of(2004, MAY, 1), LocalDate.of(2005, APRIL, 30))) {
            return BigDecimal.valueOf(0.825)
        } else if (erMellom(uttaksdato, LocalDate.of(2005, MAY, 1), LocalDate.of(2016, AUGUST, 31))) {
            return BigDecimal.valueOf(0.85)
        } else if (uttaksdato.isAfter(LocalDate.of(2016, AUGUST, 31))) {
            return BigDecimal.valueOf(0.9)
        }
    }

    return BigDecimal.ONE

}

internal fun erMellom(uttaksdato: LocalDate, t1: LocalDate, t2: LocalDate): Boolean {
    if (uttaksdato.isEqual(t1) || uttaksdato.isAfter(t1)) {
        if (uttaksdato.isBefore(t2) || uttaksdato.isEqual(t2)) {
            return true
        }
    }
    return false
}

fun main(args: Array<String>) {

}