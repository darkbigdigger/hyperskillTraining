import java.lang.Math.random

fun main(){

    val subj = arrayOf("Stone", "Scissors", "Paper")
    val compSubj = subj[rngNums(subj)]
    var userSubj = getUserChoice(subj)
    userSubj = if (userSubj in subj) userSubj else subj[userSubj.toInt()]
    printRes(compare(userSubj, compSubj))
    println("User pick: $userSubj \nComputer pick: $compSubj")
}

fun rngNums(optArr: Array<String>) = (random()*optArr.size).toInt()

fun compare(userSubj: String, compSubj: String) : Int {
    return when {
        userSubj == compSubj -> 0
        (userSubj == "Stone" && compSubj == "Scissors") ||
                (userSubj == "Scissors" && compSubj == "Paper") ||
                (userSubj == "Paper" && compSubj == "Stone") -> 1
        else -> -1
    }
}

fun printRes(compFunc: Int) {
    when (compFunc) {
        1 -> println("You win.")
        -1 -> println("Computer win")
        0 -> println("Draw :|")
    }
}


fun getUserChoice(subj: Array<String>) : String {

    println("Pick your item: \nWrite word or number:\n(0) - Stone, (1) - Scissors, (2) - Paper")
    val userChoice = readLine()
    return if ((userChoice in subj) || (userChoice?.toInt() in 0..2)) userChoice!!
    else getUserChoice(subj)
}

