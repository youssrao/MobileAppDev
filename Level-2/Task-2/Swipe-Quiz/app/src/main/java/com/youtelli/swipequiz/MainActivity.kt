package com.youtelli.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {

        rvQuiz.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuiz.adapter = questionAdapter
        rvQuiz.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        createItemTouchHelper().attachToRecyclerView(rvQuiz)


        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
    }


    // Create a touch helper to recognize when a user swipes an item from a recycler view.
    private fun createItemTouchHelper(): ItemTouchHelper {
        // User can swipe left or right.
        val callback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                //Disable the ability to move items up and down.
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                // Callback triggered when a user swiped an item to left or right.
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    var position = viewHolder.adapterPosition

                    if (direction == ItemTouchHelper.RIGHT) {
                        checkAnswerTrue(position)
                    }
                    if (direction == ItemTouchHelper.LEFT) {
                        checkAnswerFalse(position)
                    }
                    questionAdapter.notifyItemChanged(position)
                }

            }
        return ItemTouchHelper(callback)
    }


    private fun checkAnswerFalse(questionPos: Int) {
        if (!questions[questionPos].answer) {
            // If user's answer does match the solution for the question.
            Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show()
            questions.removeAt(questionPos)
            questionAdapter.notifyItemRemoved(questionPos)
            questionAdapter.notifyDataSetChanged()
        } else {
            // When it does not match.
            Toast.makeText(this, getString(R.string.incorrect_answer), Toast.LENGTH_SHORT).show()
            questionAdapter.notifyDataSetChanged()
        }
    }



    private fun checkAnswerTrue(questionPos: Int) {
        if (questions[questionPos].answer) {
            // If user's answer does match the solution for the question.
            Toast.makeText(this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show()
            questions.removeAt(questionPos)
            questionAdapter.notifyItemRemoved(questionPos)
            questionAdapter.notifyDataSetChanged()
        } else {
            // When it does not match.
            Toast.makeText(this, getString(R.string.incorrect_answer), Toast.LENGTH_SHORT).show()
            questionAdapter.notifyDataSetChanged()
        }
    }
}

