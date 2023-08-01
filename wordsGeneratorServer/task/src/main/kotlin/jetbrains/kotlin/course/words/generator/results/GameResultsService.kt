package jetbrains.kotlin.course.words.generator.results

import jetbrains.kotlin.course.words.generator.team.Team
import jetbrains.kotlin.course.words.generator.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>
@Service
class GameResultsService {
    companion object {
        private val gameHistory: MutableList<GameResult> = mutableListOf()
    }
    fun saveGameResults(result: GameResult) {
        require(result.isNotEmpty()) { "Result array is empty." }
        require(result.all { TeamService.teamsStorage.containsKey(it.id) }) { "Not all IDs from result are in the teamsStorage." }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> = gameHistory.reversed()
}