package jetbrains.kotlin.course.codenames.card

import jetbrains.kotlin.course.codenames.utils.Utils.TOTAL_NUMBER
import org.springframework.stereotype.Service
import jetbrains.kotlin.course.codenames.utils.words

@Service
class CardService {
    fun generateWordsCards(): List<Card> {
        require(words.size >= TOTAL_NUMBER)
        { "TOTAL_NUMBER(${TOTAL_NUMBER}) is more than number of words(${words.size}) " }
        words = words.shuffled()
        val generatedCards = words.take(TOTAL_NUMBER).map { Card(WordCardData(it), CardState.Front) }
        words = words.drop(TOTAL_NUMBER)
        return generatedCards
    }
}