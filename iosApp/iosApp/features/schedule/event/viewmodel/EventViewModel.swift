//
//  RaceWeekendViewModel.swift
//  iosApp
//
//  Created by Tinashe Makuti on 11/04/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Shared

final class EventViewModel: ObservableObject {
    @Published var trackSummary: TrackSummaryModel? = nil
    @Published var raceSummary: RaceSummaryModel? = nil
    @Published var isLoading: Bool = false
    

    func viewDidAppear(round: Int, year: Int, trackName: String) {
        Task {
            await fetchSummaries(round: round, year: year, trackName: trackName)
        }
    }
    
    @MainActor
    private func fetchSummaries(round: Int, year: Int, trackName: String) async {
        isLoading = true
        defer {
            isLoading = false
        }
//        await withTaskGroup(of: Void.self) { group in
//            group.addTask { @MainActor in
////                  self.raceSummary = await self.dataLogic.getRaceSummary(round: round, year: year)
//              }
//              
//            group.addTask { @MainActor in
//                  self.trackSummary = await self.dataLogic.getTrackSummary(trackName: trackName)
//              }
//              
//              await group.waitForAll()
//          }
    }
}
