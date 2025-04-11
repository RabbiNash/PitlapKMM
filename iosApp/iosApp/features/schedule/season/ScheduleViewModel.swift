//
//  SeasonCalendarViewModel.swift
//  Box Box
//
//  Created by Tinashe MAKUTI on 30/12/2024.
//

import Foundation
import Shared

final class ScheduleViewModel: ObservableObject {
    
    private let pitlapService: PitlapService
    
    init(pitlapService: PitlapService = Pitlap.shared.getService()) {
        self.pitlapService = pitlapService
    }
    
    @Published var nextSession: EventScheduleModel? = nil
    @Published var seasonCalendar: [EventScheduleModel] = []

    
    @MainActor
    func loadSchedule(year: Int, showPastEvents: Bool = false) async {
        do {
            let schedule = try await pitlapService.getSchedule(year: Int32(year))
            self.seasonCalendar = showPastEvents ? schedule : schedule.filter { self.isNextEvent(event: $0) }
            self.nextSession = getNextEvent(from: self.seasonCalendar)
        } catch {
            print("error handling")
        }
    }
    
    private func getNextEvent(from events: [EventScheduleModel]) -> EventScheduleModel? {
        return events.first(where: { isNextEvent(event: $0) })
    }
    
    private func isNextEvent(event: EventScheduleModel) -> Bool {
        let currentDate = Date()
        if event.eventFormat == "convectional" {
            return Date.getDateFromString(dateString: event.session5DateUTC!) ?? Date() > currentDate
        } else {
            return Date.getDateFromString(dateString: event.session3DateUTC!) ?? Date() > currentDate
        }
    }
}
