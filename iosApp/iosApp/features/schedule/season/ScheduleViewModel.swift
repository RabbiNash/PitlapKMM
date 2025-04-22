//
//  SeasonCalendarViewModel.swift
//  Box Box
//
//  Created by Tinashe MAKUTI on 30/12/2024.
//

import Foundation
import PitlapKit

final class ScheduleViewModel: ObservableObject {
    
    private let pitlapService: PitlapService
    
    init(pitlapService: PitlapService = Pitlap.shared.getService()) {
        self.pitlapService = pitlapService
    }
    
    @Published var nextSession: EventScheduleModel? = nil
    @Published var seasonCalendar: [EventScheduleModel] = []
    @Published var weather: WeatherModel? = nil

    
    @MainActor
    func loadSchedule(year: Int, showPastEvents: Bool = false) async {
        do {
            let schedule = try await pitlapService.getSchedule(year: Int32(year))
            self.seasonCalendar = showPastEvents ? schedule : schedule.filter { self.isNextEvent(event: $0) }
            self.nextSession = getNextEvent(from: self.seasonCalendar)
        } catch {
            print("error handling")
        }
        
        await loadWeather()
        await loadYoutube()
    }
    
    @MainActor
    func loadWeather() async {
        do {
            let weather: WeatherModel = try await pitlapService.getWeather(year: 2025, round: 1)
            
            self.weather = weather
            print(weather)
        } catch {
            print("error handling")
        }
    }
    
    @MainActor
    func loadYoutube() async {
        do {
            let videos = try await pitlapService.getYTVideos(channelName: "Formula 1")
            print(videos)
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
