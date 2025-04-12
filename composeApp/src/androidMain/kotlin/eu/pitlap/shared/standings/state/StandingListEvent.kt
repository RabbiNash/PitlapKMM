package eu.pitlap.shared.standings.state

sealed class StandingListEvent {
    data object LoadDriverStanding : StandingListEvent()
    data object LoadConstructorStanding : StandingListEvent()
}
