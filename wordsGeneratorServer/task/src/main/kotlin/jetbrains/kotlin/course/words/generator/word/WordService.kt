package jetbrains.kotlin.course.words.generator.word

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.words.generator.util.words

@Service
class WordService {
    companion object {
        val numberOfWords = words.size
        private val previousWords: MutableMap<String, MutableList<Word>> = mutableMapOf()
    }

    fun generateNextWord(): Word {
        require(words.isNotEmpty()) { "List of words is empty"}
        return Word(words.removeFirst())
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        val grouped1 = newWord.groupingBy { it }.eachCount()
        val grouped2 = keyWord.groupingBy { it }.eachCount()
        return newWord.isNotEmpty() && newWord.all { (grouped1[it] ?: 0) <= (grouped2[it] ?: 0) }
    }

    fun isNewWord(keyWord: String, newWord: String): Boolean {
        val wordList = previousWords.getOrPut(keyWord) { mutableListOf() }
        return if (wordList.contains(Word(newWord))) {
            false
        } else {
            wordList.add(Word(newWord))
            true
        }
    }
}