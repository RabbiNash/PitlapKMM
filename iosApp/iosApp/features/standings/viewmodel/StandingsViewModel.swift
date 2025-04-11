//
//  DriverStandingsViewModel.swift
//  PitLap
//
//  Created by Tinashe MAKUTI on 21/01/2025.
//

import Foundation
import Shared

final class StandingsViewModel: ObservableObject {
    private let pitlapService: PitlapService
    
    @Published var isLoading: Bool = false
    @Published var standings: [StandingRowUiModel] = []
    
    init(pitlapService: PitlapService = Pitlap.shared.getService()) {
        self.pitlapService = pitlapService
    }
    
    func getDriverStandings() async {        
        await fetchStandings { [self] in try await pitlapService.getDriverStandings().map { StandingRowUiModel(from: $0) } }
    }
    
    func getConstructorStandings() async {
        await fetchStandings { [self] in try await pitlapService.getConstructorStandings().map { StandingRowUiModel(from: $0) } }
    }
    
    @MainActor
    private func fetchStandings(_ operation: @escaping () async throws -> [StandingRowUiModel]) async {
        isLoading = true
        defer { isLoading = false }
        do {
            self.standings = try await operation()
        } catch {
            print(error.localizedDescription)
        }
    }
}

extension StandingRowUiModel {
    init(from standing: DriverStandingModel) {
        self.init(
            position: standing.positionText,
            fullName: "\(standing.givenName) \(standing.familyName)",
            constructorName: standing.constructorName,
            points: "\(standing.points)",
            wins: "\(standing.wins)"
        )
    }
    
    init(from standing: ConstructorStandingModel) {
        self.init(
            position: standing.positionText,
            fullName: nil,
            constructorName: standing.constructorName,
            points: "\(standing.points)",
            wins: "\(standing.wins)"
        )
    }
}
