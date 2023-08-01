package jetbrains.kotlin.course.words.generator.team

import org.springframework.stereotype.Service

@Service
class TeamService {
    companion object{
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }
    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = List(teamsNumber) { Team() }
        teams.forEach { teamsStorage.putIfAbsent(it.id, it) }
        return teams
    }
}