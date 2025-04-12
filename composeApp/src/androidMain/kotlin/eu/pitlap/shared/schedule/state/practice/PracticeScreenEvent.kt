package eu.pitlap.shared.schedule.state.practice

sealed class PracticeScreenEvent {
    data class LoadResults(
        val year: Int,
        val round: Int,
        val sessionName: String
    ) : PracticeScreenEvent()
}
