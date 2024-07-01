package com.dreamsoftware.fitflextv.data.repository.routine

import com.dreamsoftware.fitflextv.data.entities.Routine

interface RoutineRepository {

    fun getRoutines(): List<Routine>
    fun getRoutineById(id: String): Routine
}