package eu.pitlap.shared.navigation.destination.schedule

sealed class ScheduleNavDestination(val route: String) {
    data object EventDetails : ScheduleNavDestination("event_detail/{year}/{round}") {
        fun createRoute(year: Int, round: Int) = "event_detail/$year/$round"
    }

    data object SessionDetail : ScheduleNavDestination("session_detail/{year}/{round}/{sessionName}") {
        fun createRoute(year: Int, round: Int, sessionName: String) = "session_detail/$year/$round/$sessionName"
    }

    data object PracticeResults : ScheduleNavDestination("practice/{year}/{round}/{sessionName}") {
        fun createRoute(year: Int, round: Int, sessionName: String) = "practice/$year/$round/$sessionName"
    }

    data object QualifyingResults : ScheduleNavDestination("qualifying/{year}/{round}") {
        fun createRoute(year: Int, round: Int) = "qualifying/$year/$round"
    }

    data object RaceResults : ScheduleNavDestination("race/{year}/{round}") {
        fun createRoute(year: Int, round: Int) = "race/$year/$round"
    }

    data object TopSpeed : ScheduleNavDestination("speed/{year}/{round}/{sessionName}") {
        fun createRoute(year: Int, round: Int, sessionName: String) = "speed/$year/$round/$sessionName"
    }
}

