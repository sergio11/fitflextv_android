package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.data.repository.workout.DummyWorkoutData
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageEnum
import com.dreamsoftware.fitflextv.domain.model.SubtitleLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.util.Date

internal class WorkoutRepositoryImpl(
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IWorkoutRepository {

    private val dummyWorkoutData: DummyWorkoutData = DummyWorkoutData()
    override suspend fun getWorkouts(): List<WorkoutBO> = listOf(
        WorkoutBO(
            id = "1",
            name = "Strengthen & lengthen",
            description = "This workout, titled 'Strengthen & lengthen', is designed to help you increase your muscle strength and flexibility.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/8e91aafe-52be-41fd-8869-977ca0410ab5",
            duration = "16",
            videoUrl = "https://example.com/video1",
            intensityEnum = IntensityEnum.MEDIUM,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "2",
            name = "Battle ropes HIIT",
            description = "This high-intensity interval training session with battle ropes will push your limits and improve your cardiovascular fitness.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.CARDIO,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/41277637-6cc9-4bcd-9a1f-b4b12d501dc8",
            duration = "14",
            videoUrl = "https://example.com/video2",
            intensityEnum = IntensityEnum.HARD,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "3",
            name = "Improve strength for run",
            description = "This workout, titled 'Improve strength for run', is designed to enhance your muscle strength and endurance specifically for running. It focuses on exercises that build the key muscle groups used in running, helping you run faster and longer while reducing the risk of injury.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/b1773e6c-4a50-4e45-a8f2-5ff176e8167a",
            duration = "13",
            videoUrl = "https://example.com/video3",
            intensityEnum = IntensityEnum.MEDIUM,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "4",
            name = "Intense full body workout",
            description = "This intense full body workout will challenge your entire body and boost your strength and stamina.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.CARDIO,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/7927e3f0-bb09-4309-a11c-0748cd39b535",
            duration = "25",
            videoUrl = "https://example.com/video4",
            intensityEnum = IntensityEnum.HARD,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "5",
            name = "Workout with weights",
            description = "This workout, titled 'Workout with weights', is perfect for building muscle strength and endurance using various weight exercises.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/194152ea-d535-4504-bcae-17007d47e20a",
            duration = "12",
            videoUrl = "https://example.com/video5",
            intensityEnum = IntensityEnum.MEDIUM,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "6",
            name = "Glutes and leg strength",
            description = "Focus on your glutes and legs with this strength workout, designed to tone and build muscle in your lower body.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/f086b517-9c6a-44fa-9924-a6a5c9bde386",
            duration = "15",
            videoUrl = "https://example.com/video6",
            intensityEnum = IntensityEnum.MEDIUM,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "7",
            name = "Full body strength",
            description = "This full body strength workout will engage all major muscle groups to enhance your overall fitness.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/e83a8d1e-5d78-4ad2-8a42-341251d77e79",
            duration = "28",
            videoUrl = "https://example.com/video7",
            intensityEnum = IntensityEnum.HARD,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "8",
            name = "Strength for beginners",
            description = "This beginner-friendly strength workout is designed to introduce you to the basics of strength training.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/7236d183-0be8-4b4a-b6c9-e628e67c9bc3",
            duration = "10",
            videoUrl = "https://example.com/video8",
            intensityEnum = IntensityEnum.EASY,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "9",
            name = "Interval 5 day split",
            description = "This interval training workout is part of a 5-day split program to maximize your strength and endurance.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.CARDIO,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/2f0c3c1c-d40d-46c8-995d-3a524b521a8d",
            duration = "16",
            videoUrl = "https://example.com/video9",
            intensityEnum = IntensityEnum.MEDIUM,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "10",
            name = "Power workout",
            description = "This power-packed workout is designed to build strength, agility, and explosive power.",
            instructorName = "David Lee",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/dd02be7b-b97b-42eb-b152-11a8579d3821",
            duration = "25",
            videoUrl = "https://example.com/video10",
            intensityEnum = IntensityEnum.HARD,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "11",
            name = "Beginner abs training",
            description = "This beginner abs training workout focuses on building core strength and stability.",
            instructorName = "Emily Johnson",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/b1d53df4-2c05-4cb9-813f-0f42bcafa0d1",
            duration = "13",
            videoUrl = "https://example.com/video11",
            intensityEnum = IntensityEnum.EASY,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        ),
        WorkoutBO(
            id = "12",
            name = "Working in the gym",
            description = "This gym-focused workout emphasizes traditional exercises to help you build muscle and improve overall fitness.",
            instructorName = "Emily Johnson",
            workoutTypeEnum = WorkoutTypeEnum.STRENGTH,
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/90c1f376-adca-4915-9fdf-4c871008a75b",
            duration = "20",
            videoUrl = "https://example.com/video12",
            intensityEnum = IntensityEnum.MEDIUM,
            releasedDate = Date(),
            languageEnum = LanguageEnum.ENGLISH,
            subtitleLanguageEnum = SubtitleLanguageEnum.ENGLISH,
            subtitleUri = null
        )
    )

    override suspend fun getWorkoutById(id: String): WorkoutBO = getWorkouts().first { it.id == id }
    override suspend fun getFavoritesWorkouts() = dummyWorkoutData.list

}