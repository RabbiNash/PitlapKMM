//
//  IndeterminateProgressView.swift
//  iosApp
//
//  Created by Tinashe Makuti on 10/04/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct IndeterminateProgressView: View {
    @State private var isAnimating: Bool = false

    var body: some View {
        Capsule()
            .frame(height: 3)
            .foregroundColor(Color(
                red: 232.0 / 255,
                green: 232.0 / 255,
                blue: 232.0 / 255
            ))
            .overlay(progress)
    }

    private var progress: some View {
        GeometryReader { (geometry: GeometryProxy) in
            Capsule()
                .foregroundColor(Color.red)
                .frame(width: progressWidth(in: geometry))
                .offset(offset(in: geometry))
                .onAppear {
                    withAnimation(animation) {
                        isAnimating = true
                    }
                }
        }
    }

    private var animation: Animation? {
        .linear(duration: 1).repeatForever(autoreverses: false)
    }

    private func progressWidth(in geometry: GeometryProxy) -> CGFloat {
        geometry.size.width / 5
    }

    private func offset(in geometry: GeometryProxy) -> CGSize {
        isAnimating
        ? CGSize(width: geometry.size.width, height: 0)
        : CGSize(width: -progressWidth(in: geometry), height: 0)
    }
}
