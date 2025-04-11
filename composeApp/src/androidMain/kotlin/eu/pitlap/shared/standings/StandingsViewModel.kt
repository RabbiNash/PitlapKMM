package eu.pitlap.shared.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.pitlap.shared.modules.Pitlap
import eu.pitlap.shared.modules.PitlapService
import eu.pitlap.shared.standings.domain.model.DriverStandingModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StandingsViewModel(
    private val pitlapService: PitlapService = Pitlap.getService()
): ViewModel() {

    private val _driverStandings = MutableStateFlow<List<DriverStandingModel>>(listOf())
    val driverStandings = _driverStandings.asStateFlow()

    init {
        viewModelScope.launch {
            _driverStandings.update {
                pitlapService.getDriverStandings()
            }
        }
    }
}
