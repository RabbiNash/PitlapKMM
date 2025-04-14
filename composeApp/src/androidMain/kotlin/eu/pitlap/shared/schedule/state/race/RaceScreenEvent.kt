package eu.pitlap.shared.schedule.state.race

sealed class RaceScreenEvent {
    data class LoadResults(
        val year: Int,
        val round: Int,
    ) : RaceScreenEvent()
}
