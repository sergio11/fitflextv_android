package com.dreamsoftware.fitflextv.data.repository.impl

import com.dreamsoftware.fitflextv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.LanguageBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SubtitleLanguageBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.domain.repository.IRoutineRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.util.Date

internal class RoutineRepositoryImpl (
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IRoutineRepository {
    override fun getRoutines(): List<RoutineBO> = listOf(
        RoutineBO(
            "1",
            "10 Morning Exercises",
            "Don’t let mornings put you in a bad mood! Make your day so much better by launching yourself off your bed and getting in to a full-on workout mode.",
            "Rachel Wright",
            WorkoutTypeEnum.YOGA,
            "https://github.com/TheChance101/tv-samples/assets/45900975/8e91aafe-52be-41fd-8869-977ca0410ab5.png",
            10L,
            "https://example.com/morning_stretch_video.mp4",
            IntensityEnum.MEDIUM,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "2",
            "Morning Stretch Routine",
            "Start your day with this gentle stretching routine designed to awaken your body and improve flexibility. Led by instructor Sarah Johnson, this routine will leave you feeling refreshed and ready for the day ahead.",
            "Sarah Johnson",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/41277637-6cc9-4bcd-9a1f-b4b12d501dc8",
            20L,
            "https://example.com/morning_stretch_video.mp4",
            IntensityEnum.EASY,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "3",
            "Full Body Circuit Training",
            "Get a complete workout in just 30 minutes with this full-body circuit training routine. Led by trainer Alex Rodriguez, this high-intensity workout combines strength exercises with cardio intervals to maximize results.",
            "Alex Rodriguez",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/b1773e6c-4a50-4e45-a8f2-5ff176e8167a",
            30L,
            "https://example.com/full_body_circuit_video.mp4",
            IntensityEnum.MEDIUM,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "4",
            "Evening Yoga Relaxation",
            "Wind down and release tension with this evening yoga relaxation routine. Led by instructor Emily White, this gentle practice focuses on deep breathing and restorative poses to promote relaxation and stress relief.",
            "Emily White",
            WorkoutTypeEnum.YOGA,
            "https://github.com/TheChance101/tv-samples/assets/45900975/7927e3f0-bb09-4309-a11c-0748cd39b535",
            25L,
            "https://example.com/evening_yoga_relaxation_video.mp4",
            IntensityEnum.EASY,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "5",
            "Core Strengthening Workout",
            "Develop a strong and stable core with this core strengthening workout routine. Led by instructor Mike Smith, this series of exercises targets your abdominal muscles to improve strength and posture.",
            "Mike Smith",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/194152ea-d535-4504-bcae-17007d47e20a",
            40L,
            "https://example.com/core_strengthening_video.mp4",
            IntensityEnum.MEDIUM,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "6",
            "Cardio Blast",
            "Boost your cardiovascular fitness with this intense cardio blast routine. Led by instructor Jessica Miller, this high-energy workout includes dynamic movements to elevate your heart rate and burn calories.",
            "Jessica Miller",
            WorkoutTypeEnum.CARDIO,
            "https://github.com/TheChance101/tv-samples/assets/45900975/f086b517-9c6a-44fa-9924-a6a5c9bde386",
            35L,
            "https://example.com/cardio_blast_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "7",
            "Quick Abs Burn",
            "Get ready to feel the burn with this quick abs workout routine. Led by instructor David Lee, this intense session targets your abdominal muscles to help sculpt and define your midsection.",
            "David Lee",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/e83a8d1e-5d78-4ad2-8a42-341251d77e79",
            15L,
            "https://example.com/quick_abs_burn_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "8",
            "Total Body Stretch",
            "Release tension and improve flexibility with this total body stretch routine. Led by instructor Maria Garcia, this gentle sequence of stretches targets all major muscle groups to help you feel relaxed and rejuvenated.",
            "Maria Garcia",
            WorkoutTypeEnum.STRENGTH,
            "https://github.com/TheChance101/tv-samples/assets/45900975/7236d183-0be8-4b4a-b6c9-e628e67c9bc3",
            25L,
            "https://example.com/total_body_stretch_video.mp4",
            IntensityEnum.EASY,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "9",
            "Power Yoga Flow",
            "Ignite your inner strength with this power yoga flow routine. Led by instructor Rachel Adams, this dynamic sequence of poses focuses on building strength, flexibility, and balance.",
            "Rachel Adams",
            WorkoutTypeEnum.YOGA,
            "https://github.com/TheChance101/tv-samples/assets/45900975/194152ea-d535-4504-bcae-17007d47e20a",
            40L,
            "https://example.com/power_yoga_flow_video.mp4",
            IntensityEnum.MEDIUM,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        ),
        RoutineBO(
            "10",
            "Interval Training Challenge",
            "Push your limits with this interval training challenge routine. Led by trainer Chris Johnson, this high-intensity workout alternates between bursts of intense exercise and brief recovery periods to maximize calorie burn and improve fitness.",
            "Chris Johnson",
            WorkoutTypeEnum.CARDIO,
            "https://github.com/TheChance101/tv-samples/assets/45900975/7927e3f0-bb09-4309-a11c-0748cd39b535",
            50L,
            "https://example.com/interval_training_challenge_video.mp4",
            IntensityEnum.HARD,
            Date(),
            LanguageBO.ENGLISH,
            SubtitleLanguageBO.ENGLISH
        )
    )

    override fun getRoutineById(id: String): RoutineBO = getRoutines().first { it.id == id }
}