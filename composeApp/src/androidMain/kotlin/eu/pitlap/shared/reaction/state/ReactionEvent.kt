package eu.pitlap.shared.reaction.state

sealed interface ReactionEvent {
    object ButtonPressed : ReactionEvent
}
