//
//  OldRacesView.swift
//  PitLap
//
//  Created by Tinashe Makuti on 19/03/2025.
//

import SwiftUI

struct PastEventsView: View {
    @State var selectedTab: SeasonTabOption = .current
   
    @StateObject private var viewModel: ScheduleViewModel
    
    init(viewModel: ScheduleViewModel = ScheduleViewModel()) {
        self._viewModel = StateObject(wrappedValue: viewModel)
    }

    var body: some View {
        NavigationStack {
            ScrollView(showsIndicators: false) {
                LazyVStack {
                    SeasonPicker(selectedOption: $selectedTab)
                        .padding()
                    
                    Group {
                        switch selectedTab {
                        case .current:
                            seasonView
                                .task {
                                    await viewModel.loadSchedule(year: 2025, showPastEvents: true)
                                }
                        case .past:
                            seasonView
                                .task {
                                    await viewModel.loadSchedule(year: 2025, showPastEvents: true)
                                }
                        }
                    }
                }
            }.padding(24)
        }
    }
    
    @ViewBuilder
    private var seasonView: some View {
        VStack {
            ForEach(viewModel.seasonCalendar, id: \.self) { raceWeekend in
                NavigationLink(destination: EventView(event: raceWeekend)) {
                    EventListItemView(event: raceWeekend)
                }.buttonStyle(PlainButtonStyle())
            }
        }
    }
}
