//
//  SessionItemView.swift
//  iosApp
//
//  Created by Tinashe Makuti on 11/04/2025.
//  Copyright © 2025 orgName. All rights reserved.
//


import SwiftUI

struct SessionItemView: View {
    private let sessionName: String
    private let sessionTime: String

    init(sessionName: String, sessionTime: String) {
        self.sessionName = sessionName
        self.sessionTime = sessionTime
    }

    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(sessionName)
                    .frame(maxWidth: 120, alignment: .leading)

                Spacer()
                
                if isPastEvent(sessionTime: sessionTime) {
                    Text("View Results")
                } else {
                    Text(Date.getHumanisedDate(dateString: sessionTime) ?? " ")
                        .lineLimit(2)
                }

               
            }
            .frame(alignment: .leading)
            .padding(.top, 8)
            .padding(.bottom, 12)

            Divider()
        }
    }
    
    private func isPastEvent(sessionTime: String) -> Bool {
        let currentDate = Date()
        return Date.getDateFromString(dateString: sessionTime) ?? Date() < currentDate
    }
}
