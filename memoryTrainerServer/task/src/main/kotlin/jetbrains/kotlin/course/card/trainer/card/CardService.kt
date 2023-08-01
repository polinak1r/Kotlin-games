package jetbrains.kotlin.course.card.trainer.card

import jetbrains.kotlin.course.card.trainer.util.countries
import org.springframework.stereotype.Service

@Service
class CardService {
    companion object {
        private val randomCardGenerator = CardSequenceGenerator {
            return@CardSequenceGenerator countries.map { Card(Front(it.key), Back(it.value)) }.shuffled()
        }
        private fun generateNewCardsSequence() = randomCardGenerator.generateCards().toMutableList()
        private var cards = generateNewCardsSequence()
    }
    fun getNextCard(): Card {
        require(cards.isNotEmpty()) { "List of cards is empty!" }
        return cards.removeFirst()
    }
    fun startNewGame(): Card {
        cards = generateNewCardsSequence()
        return getNextCard()
    }
}
