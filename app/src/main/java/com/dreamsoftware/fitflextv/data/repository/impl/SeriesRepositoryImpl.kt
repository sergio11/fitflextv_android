package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.SongBO
import com.dreamsoftware.fitflextv.domain.model.SubtitleLanguageBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.domain.repository.ISeriesRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.util.Date

internal class SeriesRepositoryImpl(
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ISeriesRepository {

    override fun getSongById(id: String): SongBO {
        return SongBO(
            id = "123456sdasdsa",
            title = "Power Workout",
            author = "Jake Diaz",
            createdDate = "2021",
            audioUrl = "",
            imageUrl = "https://github.com/TheChance101/tv-samples/assets/45900975/7927e3f0-bb09-4309-a11c-0748cd39b535",
        )
    }

    override fun getSeries(): List<SeriesBO> = listOf(
        SeriesBO(
            "1",
            "Interval 5 day split",
            "Maximize your workouts with split training! Increase strength and build muscle in this 1-week guided program using heavier weights and targeting specific areas of the body in each workout. Repeat and track your progress!",
            "Andrew Trace",
            WorkoutTypeEnum.SESSIONS,
            "https://github.com/TheChance101/tv-samples/assets/45900975/8e91aafe-52be-41fd-8869-977ca0410ab5",
            1L,
            5,
            30,
            "https://example.com/cardio_challenge_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "2",
            "30-Day Cardio Challenge",
            "Take your fitness to the next level with our 30-day cardio challenge. Join trainer Alex Rodriguez in a series of high-intensity workouts designed to boost your endurance and burn calories.",
            "Alex Rodriguez",
            WorkoutTypeEnum.CARDIO,
            "https://github.com/TheChance101/tv-samples/assets/45900975/41277637-6cc9-4bcd-9a1f-b4b12d501dc8",
            4L,
            20,
            45,
            "https://example.com/cardio_challenge_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "3",
            "Strength Training Bootcamp",
            "Transform your physique with our intense strength training bootcamp. Led by fitness expert Mike Smith, this series includes challenging workouts to build muscle and increase strength.",
            "Mike Smith",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/b1773e6c-4a50-4e45-a8f2-5ff176e8167a",
            6L,
            24,
            40,
            "https://example.com/strength_training_bootcamp_video.mp4",
            IntensityEnum.MEDIUM,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "4",
            "Beginner Pilates Program",
            "Discover the benefits of Pilates with this beginner-friendly program. Led by instructor Emily White, this series focuses on core strength, flexibility, and body awareness.",
            "Emily White",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/7927e3f0-bb09-4309-a11c-0748cd39b535",
            4L,
            16,
            35,
            "https://example.com/beginner_pilates_program_video.mp4",
            IntensityEnum.EASY,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "5",
            "Yoga for Stress Relief",
            "Relieve stress and unwind with our yoga series designed to promote relaxation and mental well-being. Join instructor Lisa Taylor in a series of gentle yoga practices and guided meditation sessions.",
            "Lisa Taylor",
            WorkoutTypeEnum.YOGA,
            "https://github.com/TheChance101/tv-samples/assets/45900975/194152ea-d535-4504-bcae-17007d47e20a",
            3L,
            12,
            25,
            "https://example.com/yoga_stress_relief_video.mp4",
            IntensityEnum.EASY,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "6",
            "Total Body Burn",
            "Torch calories and sculpt your physique with this high-intensity total body workout series. Led by trainer Chris Johnson, each session targets multiple muscle groups for maximum results.",
            "Chris Johnson",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/f086b517-9c6a-44fa-9924-a6a5c9bde386",
            5L,
            20,
            40,
            "https://example.com/total_body_burn_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "7",
            "Prenatal Yoga Series",
            "Stay active and prepare for childbirth with our prenatal yoga series. Led by experienced instructor Rachel Adams, these gentle practices focus on strengthening the body and calming the mind during pregnancy.",
            "Rachel Adams",
            WorkoutTypeEnum.YOGA,
            "https://github.com/TheChance101/tv-samples/assets/45900975/e83a8d1e-5d78-4ad2-8a42-341251d77e79",
            3L,
            10,
            30,
            "https://example.com/prenatal_yoga_video.mp4",
            IntensityEnum.EASY,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "8",
            "Advanced HIIT Workouts",
            "Challenge yourself with our advanced HIIT series designed for experienced fitness enthusiasts. Led by trainer Mark Thompson, these high-intensity interval workouts will push your limits and take your fitness to new heights.",
            "Mark Thompson",
            WorkoutTypeEnum.CARDIO,
            "https://github.com/TheChance101/tv-samples/assets/45900975/7236d183-0be8-4b4a-b6c9-e628e67c9bc3",
            6L,
            18,
            50,
            "https://example.com/advanced_hiit_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        SeriesBO(
            "9",
            "Core Strength Challenge",
            "Build a strong and stable core with our intensive core strength challenge series. Led by instructor Jessica Miller, these targeted workouts will help you develop abdominal strength and improve posture.",
            "Jessica Miller",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/194152ea-d535-4504-bcae-17007d47e20a",
            4L,
            16,
            35,
            "https://example.com/core_strength_challenge_video.mp4",
            IntensityEnum.MEDIUM,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        )
    )

    override fun getSeriesById(id: String): SeriesBO = getSeries().first { it.id == id }

}