package com.dreamsoftware.fitflextv.data.repository.routine

import com.dreamsoftware.fitflextv.domain.model.RoutineBO

interface RoutineRepository {

    fun getRoutines(): List<RoutineBO>
    fun getRoutineById(id: String): RoutineBO
}