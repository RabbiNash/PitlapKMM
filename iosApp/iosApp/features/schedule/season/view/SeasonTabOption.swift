//
//  SeasonTabOption.swift
//  iosApp
//
//  Created by Tinashe Makuti on 11/04/2025.
//  Copyright © 2025 orgName. All rights reserved.
//


enum SeasonTabOption: String, CaseIterable {
    case current
    case past

    var title: String {
        switch self {
        case .current: return "Current"
        case .past: return "Past"
        }
    }
}
