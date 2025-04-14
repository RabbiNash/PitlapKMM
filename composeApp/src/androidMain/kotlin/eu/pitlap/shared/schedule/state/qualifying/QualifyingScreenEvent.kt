package eu.pitlap.shared.schedule.state.qualifying

sealed class QualifyingScreenEvent {
    data class LoadResults(
        val year: Int,
        val round: Int,
    ) : QualifyingScreenEvent()
}
