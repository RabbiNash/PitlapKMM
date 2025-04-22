//
//  RaceWeekendHeaderView.swift
//  Box Box
//
//  Created by Tinashe MAKUTI on 30/12/2024.
//

import SwiftUI
import PitlapKit

struct EventHeaderView: View {
    private let event: EventScheduleModel

    @AppStorage("selectedTeam") private var selectedTeam: String = F1Team.ferrari.rawValue

    init(event: EventScheduleModel) {
        self.event = event
    }

    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 16)
                .fill(ThemeManager.shared.selectedTeamColor.gradient)
                .shadow(radius: 8)

            VStack(alignment: .leading) {
                (Text("Round ") + Text("\(event.round)"))
                    .font(.custom("Noto Sans",size: 20))
                    .foregroundStyle(.white)
                    .fontWeight(.semibold)

                Text(event.officialEventName)
                    .font(.custom("Audiowide",size: 28))
                    .fontWeight(.bold)
                    .foregroundStyle(.white)

                HStack {
                    VStack(alignment: .leading) {
                        Text(event.country)
                            .font(.custom("Noto Sans",size: 20))
                            .fontWeight(.regular)
                            .foregroundStyle(.white)
                        
                        Text(Date.getHumanisedDate(dateString: event.session1DateUTC ?? "") ?? " ")
                            .font(.custom("Noto Sans",size: 20))
                            .fontWeight(.regular)
                            .foregroundStyle(.white)
                    }
                    
                    Spacer()
                    
                    if let track = RaceTrack.fromEventName(event.eventName) {
                        Image(track.rawValue)
                            .resizable()
                            .frame(width: 48, height: 48)
                    }
                }
            }
            .padding()
        }
    }
}
