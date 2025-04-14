package eu.pitlap.shared.reaction.state

data class ReactionState(
    val activeIndex: Int = -1,
    val reactionTime: Double? = null,
    val isSequenceRunning: Boolean = false,
    val isJumpStart: Boolean = false
)
