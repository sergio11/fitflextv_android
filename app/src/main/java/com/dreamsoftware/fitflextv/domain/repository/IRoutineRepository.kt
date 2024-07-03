package com.dreamsoftware.fitflextv.domain.repository

import com.dreamsoftware.fitflextv.domain.model.RoutineBO

interface IRoutineRepository {

    fun getRoutines(): List<RoutineBO>
    fun getRoutineById(id: String): RoutineBO
}