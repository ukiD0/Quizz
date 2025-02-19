package com.example.quizz

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository)
    }

    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(excepted, actual)

        actual = viewModel.chooseFirst()
        excepted = GameUiState.choiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvalivleToChoose(text = "c1"),
                ChoiceUiState.AvalivleToChoose(text = "c2"),
                ChoiceUiState.AvalivleToChoose(text = "c3"),
                ChoiceUiState.AvalivleToChoose(text = "c4")
            )
        )
        assertEquals(excepted, actual)

        actual = viewModel.check()
        excepted = GameUiState.AnswerChecked(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvalivleToChoose(text = "c2"),
                ChoiceUiState.NotAvalivleToChoose(text = "c3"),
                ChoiceUiState.NotAvalivleToChoose(text = "c4")
            )
        )
        assertEquals(excepted, actual)
    }

    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var excepted: GameUiState = AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(excepted, actual)

        actual = viewModel.chooseFirst()
        excepted = GameUiState.choiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvalivleToChoose(text = "c1"),
                ChoiceUiState.AvalivleToChoose(text = "c2"),
                ChoiceUiState.AvalivleToChoose(text = "c3"),
                ChoiceUiState.AvalivleToChoose(text = "c4")
            )
        )
        assertEquals(excepted, actual)

        actual = viewModel.chooseSecond()
        excepted = GameUiState.choiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvalivleToChoose(text = "c1"),
                ChoiceUiState.NotAvalivleToChoose(text = "c2"),
                ChoiceUiState.AvalivleToChoose(text = "c3"),
                ChoiceUiState.AvalivleToChoose(text = "c4")
            )
        )
        assertEquals(excepted, actual)

        actual = viewModel.chooseThird()
        excepted = GameUiState.choiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvalivleToChoose(text = "c1"),
                ChoiceUiState.AvalivleToChoose(text = "c2"),
                ChoiceUiState.NotAvalivleToChoose(text = "c3"),
                ChoiceUiState.AvalivleToChoose(text = "c4")
            )
        )
        assertEquals(excepted, actual)

        actual = viewModel.chooseForth()
        excepted = GameUiState.choiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvalivleToChoose(text = "c1"),
                ChoiceUiState.AvalivleToChoose(text = "c2"),
                ChoiceUiState.AvalivleToChoose(text = "c3"),
                ChoiceUiState.NotAvalivleToChoose(text = "c4")
            )
        )
        assertEquals(excepted, actual)

        actual = viewModel.check()
        excepted = GameUiState.AnswerChecked(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvalivleToChoose(text = "c2"),
                ChoiceUiState.NotAvalivleToChoose(text = "c3"),
                ChoiceUiState.Incorrect(text = "c4")
            )
        )
        assertEquals(excepted, actual)

        actual = viewModel.next()
        excepted = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(excepted, actual)
    }
}

private class FakeRepository : GameRepository {
    //todo
}
