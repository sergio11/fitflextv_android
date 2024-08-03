package com.dreamsoftware.fitflextv.utils

interface IOneSideMapper<IN, OUT> {
    fun mapInToOut(input: IN): OUT
    fun mapInListToOutList(input: Iterable<IN>): Iterable<OUT>
}